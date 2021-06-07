package com.zhonghe.active4j.func.pay.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.util.ArithUtil;
import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.core.util.ShiroUtils;
import com.zhonghe.active4j.func.pay.dao.FuncWeixinPayPreOrderDao;
import com.zhonghe.active4j.func.pay.entity.FuncWeixinPayPreOrderEntity;
import com.zhonghe.active4j.func.pay.model.PayConstrant;
import com.zhonghe.active4j.func.pay.properties.WeixinPayProperties;
import com.zhonghe.active4j.func.pay.service.FuncWeixinPayPreOrderService;
import com.zhonghe.active4j.func.pay.util.WeChatUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @title WeixinPayPreOrderServiceImpl.java
 * @description 
		  微信预下单列表
 * @time  2019年12月10日 下午4:35:12
 * @author mhm
 * @version 1.0
*/
@Service("weixinPayPreOrderService")
@Transactional
@Slf4j
public class FuncWeixinPayPreOrderServiceImpl extends ServiceImpl<FuncWeixinPayPreOrderDao, FuncWeixinPayPreOrderEntity> implements FuncWeixinPayPreOrderService {

	
	@Autowired
	private WeixinPayProperties weixinPayProperties;
	
	/**
	 * 
	 * @description
	 *  	获取预下单实体
	 * @params
	 *      outTradeNo
	 * @return FuncWeixinPayPreOrderEntity
	 * @author mhm
	 * @time 2019年12月11日 下午4:15:14
	 */
	public FuncWeixinPayPreOrderEntity findOrder(String outTradeNo) {
		
		QueryWrapper<FuncWeixinPayPreOrderEntity> queryWrapper = new QueryWrapper<FuncWeixinPayPreOrderEntity>();
		queryWrapper.eq("OUT_TRADE_NO", outTradeNo);
		List<FuncWeixinPayPreOrderEntity> lstPayOrder = this.list(queryWrapper);
		
		if(null != lstPayOrder && lstPayOrder.size() > 0) {
			return lstPayOrder.get(0);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @description
	 *  	微信支付核心方法  
	 * @params
	 *      orderNo  商品订单号
	 *      money    金额
	 * @return void
	 * @author mhm
	 * @time 2019年12月11日 下午2:29:44
	 */
	@Override
	public AjaxJson doWeixinPay(String orderNo, Double money) {
		AjaxJson j = new AjaxJson();
		if(StringUtils.isEmpty(weixinPayProperties.getAppId()) || StringUtils.isEmpty(weixinPayProperties.getMchId())
				|| StringUtils.isEmpty(weixinPayProperties.getNotifyUrl()) || StringUtils.isEmpty(weixinPayProperties.getKey())) {
			j.setSuccess(false);
			j.setMsg("缺少必要的微信支付参数");
			return j;
		}
		log.info("订单号{}进入微信支付", orderNo);
		
		//支付商品说明
		String body = "演示微信支付付款" + money + "元";
		//获取支付IP
		String ip = ShiroUtils.getSession().getHost();
		//创建微信统一下单实体  详情参考微信支付文档
		FuncWeixinPayPreOrderEntity preOrder = createPrePayOder(money, orderNo, body, body, "付款", orderNo, ip, null, null, false, false);
		try {	
			//请求微信支付 得到map结果集
			Map<String, String> result = getXmlStrFromPreOrder(preOrder);
			String returnCode = result.get("return_code");
			//失败返回
			if(StringUtils.equalsIgnoreCase(WeChatUtil.FAIL, returnCode)) {
				j.setSuccess(false);
				j.setMsg(result.get("return_msg"));
				
				//失败赋值
				preOrder.setStatus(PayConstrant.pre_order_status_submit_fail);
				preOrder.setErrMsg(result.get("return_msg"));
				preOrder.setErrCode(result.get("return_msg"));
				return j;
			}
			
			//成功返回
			if(StringUtils.equalsIgnoreCase(WeChatUtil.SUCCESS, returnCode)) {
				String resultCode = result.get("result_code");
				if(StringUtils.equalsIgnoreCase(WeChatUtil.FAIL, resultCode)) {
					//结果失败
					j.setSuccess(false);
					j.setMsg("微信支付系统出错，错误代码:" + result.get("err_code") + ", 错误信息:" + result.get("err_code_des"));
					
					//失败赋值
					preOrder.setStatus(PayConstrant.pre_order_status_submit_fail);
					preOrder.setErrMsg(result.get("err_code_des"));
					preOrder.setErrCode(result.get("err_code"));
					
					return j;
				}else if(StringUtils.equalsIgnoreCase(WeChatUtil.SUCCESS, resultCode)) {
					//结果成功
					String prepayId = result.get("prepay_id");
					String codeUrl = result.get("code_url");
					log.info("订单号{},产生的预下单ID:{}, 生成的code为{}.", orderNo, prepayId, codeUrl);
					
					//成功赋值
					preOrder.setPrepayId(prepayId);
					preOrder.setCodeUrl(codeUrl);
					preOrder.setStatus(PayConstrant.pre_order_status_submit_success);
					
					//赋值返回页面的数据  生成页面二维码
					j.setObj(codeUrl);
					
				}
			}
		}catch(Exception e) {
			log.error("用户{}微信支付遇到异常，异常信息:{}",ShiroUtils.getSessionUserId(), e.getMessage());
			log.error(e.getMessage(), e);
			j.setSuccess(false);
			j.setMsg("微信支付遇到异常，系统工程师正在解决中......");
			//失败赋值
			preOrder.setStatus(PayConstrant.pre_order_status_submit_fail);
			preOrder.setErrMsg(e.getMessage());
			preOrder.setErrCode(e.getMessage());
		} finally {
			//保存下单实体
			this.save(preOrder);
			log.info("微信下单实体成功保存,订单号{}." , preOrder.getOutTradeNo());
		}
		
		return j;
	}
	
	/**
	 * 根据订单信息 生成微信支付 预下单实体
	 * @param money     订单金额
	 * @param body      商品简要描述
	 * @param detail    商品详细描述
	 * @param attach    标识，原样返回
	 * @param productId 产品ID
	 * @param ip        用户终端IP
	 * @param openId    用户唯一微信标识
	 * @param sceneInfo 场景信息
	 * @param isCredit  是否支持信用卡
	 * @param isInvoice 是否需要开发票
	 * @return
	 */
	private FuncWeixinPayPreOrderEntity createPrePayOder(double money, String orderNo, String body, String detail, String attach, String productId, String ip, String openId, String sceneInfo, boolean isCredit, boolean isInvoice) {
		FuncWeixinPayPreOrderEntity preOrder = new FuncWeixinPayPreOrderEntity();
		//赋值当前登录用户
		preOrder.setCusId(ShiroUtils.getSessionUserId());
		//赋值创建状态
		preOrder.setStatus(PayConstrant.pre_order_status_created);  
		//微信支付分配的公众账号ID
		preOrder.setAppId(weixinPayProperties.getAppId());
		//微信支付分配的商户号
		preOrder.setMchId(weixinPayProperties.getMchId());
		preOrder.setDeviceInfo("WEB");
		
		//随机字符串  8位随机数
		String nonceStr = RandomStringUtils.random(20, "0123456789abcdefghijklmnopqrstuvwxyz");
		preOrder.setNonceStr(nonceStr);
		preOrder.setSignType("MD5");
		
		//商品描述
		preOrder.setBody(body);
		if(StringUtils.isNotEmpty(detail)) {
			preOrder.setDetail(detail);
		}
		
		//附加数据,原样返回
		if(StringUtils.isNotEmpty(attach)) {
			preOrder.setAttach(attach);
		}
		
		//商户订单号
		preOrder.setOutTradeNo(orderNo);
		
		preOrder.setFeeType("CNY");
		
		//支付金额   微信支付是以分为单位
		double d100 = ArithUtil.mul(money, 100.0);
		preOrder.setTotalFee((int)d100);
		
		//终端IP
		preOrder.setSpBillCreateIp(ip);
		
		//交易时间
		preOrder.setTimeStart(DateUtils.getDate2StrYYYYMMDDHHMMSS());
		preOrder.setTimeExpire(DateUtils.getDate2StrYYYYMMDDHHMMSSAdd30M());
		//回调地址
		preOrder.setNotifyUrl(weixinPayProperties.getNotifyUrl());
		//交易类型
		preOrder.setTradeType("NATIVE");
		//产品ID
		preOrder.setProductId(productId);
		
		//是否支持信用卡支付
		if(!isCredit) {
			preOrder.setLimitPay("no_credit");
		}
		
		//用户表示   JSSDK必传
		preOrder.setOpenId(openId);
		
		//需要开票
		if(isInvoice) {
			preOrder.setReceipt("Y");
		}else {
			preOrder.setReceipt("N");
		}
		preOrder.setSceneInfo(sceneInfo);
		
		return preOrder;
	}
	
	
	private Map<String, String> getXmlStrFromPreOrder(FuncWeixinPayPreOrderEntity preOrder) throws Exception {
		Map<String, String> paramMap = new TreeMap<String, String>();
		paramMap.put("appid", preOrder.getAppId());
		paramMap.put("mch_id", preOrder.getMchId());
		paramMap.put("attach", preOrder.getAttach());
		paramMap.put("device_info", preOrder.getDeviceInfo());
		paramMap.put("nonce_str", preOrder.getNonceStr());
		paramMap.put("body", preOrder.getBody());
		paramMap.put("detail", preOrder.getDetail());
		paramMap.put("out_trade_no", preOrder.getOutTradeNo());
		paramMap.put("fee_type", preOrder.getFeeType());
		paramMap.put("total_fee", String.valueOf(preOrder.getTotalFee()));
		paramMap.put("spbill_create_ip", preOrder.getSpBillCreateIp());
		paramMap.put("time_start", preOrder.getTimeStart());
		paramMap.put("time_expire", preOrder.getTimeExpire());
		paramMap.put("notify_url", preOrder.getNotifyUrl());
		paramMap.put("trade_type", preOrder.getTradeType());
		if(StringUtils.isNotEmpty(preOrder.getOpenId())) {
			paramMap.put("openid", preOrder.getOpenId());
		}
		if(StringUtils.isNotEmpty(preOrder.getProductId())) {
			paramMap.put("product_id", preOrder.getProductId());
		}
		
		if(StringUtils.isNotEmpty(preOrder.getLimitPay())) {
			paramMap.put("limit_pay", preOrder.getLimitPay());
		}
		
		paramMap.put("receipt", preOrder.getReceipt());
		
		if(StringUtils.isNotEmpty(preOrder.getSceneInfo())) {
			paramMap.put("scene_info", preOrder.getSceneInfo());
		}
		
		//签名
		String sign = WeChatUtil.generateSignature(paramMap, weixinPayProperties.getKey());
		
		log.info("获得签名:{}", sign);
		
		//获得请求参数
		String strXml = WeChatUtil.getParamXml(paramMap, sign);
		
		log.info("获得请求参数:{}", strXml);
		
		Map<String, String> mapResult = WeChatUtil.getResultMapWithoutCert(strXml);
		
		return mapResult;
	}

	
	/****************************************************微信订单查询*********************************************************************************/

	/**
	 * 
	 * @description
	 *  	根据用户ID  订单号 查询微信订单状态
	 * @params
	 *      userId
	 *      outTradeNo
	 * @return String
	 * @author mhm
	 * @time 2019年12月11日 下午5:14:41
	 */
	public String getWeixinPayStatus(String userId, String outTradeNo) {
		String payStatus = null;
		//查询数据库订单状态
		QueryWrapper<FuncWeixinPayPreOrderEntity> queryWrapper = new QueryWrapper<FuncWeixinPayPreOrderEntity>();
		queryWrapper.eq("CUS_ID", userId);
		queryWrapper.eq("OUT_TRADE_NO", outTradeNo);
		List<FuncWeixinPayPreOrderEntity> lstPreOrders = this.list(queryWrapper);
		if(null != lstPreOrders && lstPreOrders.size() > 0) {
			FuncWeixinPayPreOrderEntity preOrder = lstPreOrders.get(0);
			
			if(StringUtils.equals(preOrder.getStatus(), PayConstrant.pre_order_status_pay_fail) 
					|| StringUtils.equals(preOrder.getStatus(), PayConstrant.pre_order_status_submit_fail)) {
				//提交失败   支付失败的处理
				payStatus = PayConstrant.weixin_pay_status_fail;
				
			}else if(StringUtils.equals(preOrder.getStatus(), PayConstrant.pre_order_status_created)) {
				//创建订单
				payStatus = PayConstrant.weixin_pay_status_paying;
				
			}else if(StringUtils.equals(preOrder.getStatus(), PayConstrant.pre_order_status_pay_success)) {
				//订单支付成功
				payStatus = PayConstrant.weixin_pay_status_success;
				
			}else if(StringUtils.equals(preOrder.getStatus(), PayConstrant.pre_order_status_submit_success)) {
				//订单提交成功
				payStatus = PayConstrant.weixin_pay_status_paying;
			}
			
		}
		
		return payStatus;
	}

}
