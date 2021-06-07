package com.zhonghe.active4j.func.pay.service.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.core.util.ShiroUtils;
import com.zhonghe.active4j.func.pay.dao.FuncAliPayOrderDao;
import com.zhonghe.active4j.func.pay.entity.FuncAliPayOrderEntity;
import com.zhonghe.active4j.func.pay.model.AliPayOrderModel;
import com.zhonghe.active4j.func.pay.model.PayConstrant;
import com.zhonghe.active4j.func.pay.properties.AliPayProperties;
import com.zhonghe.active4j.func.pay.service.FuncAliPayOrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @title FuncAliPayOrderServiceImpl.java
 * @description 
		   支付宝预下单列表
 * @time  2019年12月16日 下午1:59:37
 * @author mhm
 * @version 1.0
*/
@Service("funcAliPayOrderService")
@Transactional
@Slf4j
public class FuncAliPayOrderServiceImpl extends ServiceImpl<FuncAliPayOrderDao, FuncAliPayOrderEntity> implements FuncAliPayOrderService {
	
	public static final String req_pay_url = "https://openapi.alipay.com/gateway.do";

	// 销售产品码，与支付宝签约的产品码名称。
	public static final String product_code = "FAST_INSTANT_TRADE_PAY";
	
	@Autowired
	private AliPayProperties aliPayProperties;
	
	/**
	 * 
	 * @description
	 *  	支付宝支付
	 * @params
	 *      outTradeNo
	 *      money
	 * @return AjaxJson
	 * @author mhm
	 * @time 2019年12月16日 下午2:39:24
	 */
	public AjaxJson doAliPay(String outTradeNo, double money) {
		AjaxJson j = new AjaxJson();
		if(StringUtils.isEmpty(aliPayProperties.getAppId()) || StringUtils.isEmpty(aliPayProperties.getPrivateKey())
				|| StringUtils.isEmpty(aliPayProperties.getCharset()) || StringUtils.isEmpty(aliPayProperties.getAliPublicKey())
				|| StringUtils.isEmpty(aliPayProperties.getSignType()) || StringUtils.isEmpty(aliPayProperties.getReturnUrl())
				|| StringUtils.isEmpty(aliPayProperties.getNotifyUrl())) {
			j.setSuccess(false);
			j.setMsg("缺少必要的微信支付参数");
			return j;
		}
		
		AlipayClient alipayClient = new DefaultAlipayClient(req_pay_url, aliPayProperties.getAppId(), 
				aliPayProperties.getPrivateKey(), "json", aliPayProperties.getCharset(), aliPayProperties.getPublicKey(), aliPayProperties.getSignType());
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setReturnUrl(aliPayProperties.getReturnUrl());
		request.setNotifyUrl(aliPayProperties.getNotifyUrl());
		
		log.info("订单号{}进入支付宝支付", outTradeNo);
		
		//支付商品说明
		String body = "演示支付宝支付付款" + money + "元";
		
		// 构建支付实体
		AliPayOrderModel order = new AliPayOrderModel();
		order.setBody(body);
		order.setOut_trade_no(outTradeNo);
		order.setPassback_params("付款");
		order.setSubject(body);
		order.setTotal_amount(String.valueOf(money));
		order.setDisable_pay_channels("credit_group");
		request.setBizContent(JSON.toJSONString(order));
				
		String form = "";
		try {
		
			AlipayTradePagePayResponse resp = alipayClient.pageExecute(request);
			form = resp.getBody(); //调用SDK生成表单
			log.info("订单号：{},支付金额:{},下单返回结果为:code:{}, msg:{}, subcode:{}, submsg:{},issuccess:{}",outTradeNo, String.valueOf(money), resp.getCode(), resp.getMsg(), resp.getSubCode(), resp.getSubMsg(),resp.isSuccess());
			
			FuncAliPayOrderEntity payOrder = createAliPayEntity(order, resp);
			this.save(payOrder);
			j.setObj(form);
			
		} catch (AlipayApiException e) {
			log.error("用户{}支付宝支付遇到异常，异常信息:{}",ShiroUtils.getSessionUserId(), e.getMessage());
			log.error(e.getMessage(), e);
			j.setSuccess(false);
			j.setMsg("支付宝支付遇到异常，系统工程师正在解决中......");
		}
		
		return j;
		}

	/**
	 * 创建阿里支付的订单实体
	 * @param order
	 * @param resp
	 * @return
	 */
	private FuncAliPayOrderEntity createAliPayEntity(AliPayOrderModel order, AlipayTradePagePayResponse resp) {
		FuncAliPayOrderEntity payOrder = new FuncAliPayOrderEntity();
		payOrder.setBody(order.getBody());
		payOrder.setSubject(order.getSubject());
		payOrder.setCode(resp.getCode());
		payOrder.setGoodsDetail(order.getGoods_detail());
		payOrder.setMerchantOrderNo(resp.getMerchantOrderNo());
		payOrder.setMsg(resp.getMsg());
		payOrder.setOutTradeNo(order.getOut_trade_no());
		payOrder.setPassbackParams(order.getPassback_params());
		payOrder.setProductCode(order.getProduct_code());
		payOrder.setSellerId(resp.getSellerId());
		payOrder.setSubCode(resp.getSubCode());
		payOrder.setSubMsg(resp.getSubMsg());
		payOrder.setTotalAmount(Double.valueOf(order.getTotal_amount()));
		payOrder.setTradeNo(resp.getTradeNo());
		payOrder.setOrderTime(DateUtils.getNow());
		payOrder.setCusId(ShiroUtils.getSessionUserId());
		if(resp.isSuccess()) {
			payOrder.setStatus("2"); //提交成功
		}else {
			payOrder.setStatus("1"); //提交失败
		}
		
		return payOrder;
	}

	/**
	 * 
	 * @description
	 *  	根据用户ID  订单号 查询支付宝订单状态
	 * @params
	 * 		userId
	 *      orderNo
	 * @return String
	 * @author mhm
	 * @time 2019年12月16日 下午4:25:52
	 */
	public String getAliPayStatus(String userId, String orderNo) {
		String payStatus = null;
		//查询数据库订单状态
		QueryWrapper<FuncAliPayOrderEntity> queryWrapper = new QueryWrapper<FuncAliPayOrderEntity>();
		queryWrapper.eq("CUS_ID", userId);
		queryWrapper.eq("OUT_TRADE_NO", orderNo);
		List<FuncAliPayOrderEntity> lstPreOrders = this.list(queryWrapper);
		if(null != lstPreOrders && lstPreOrders.size() > 0) {
			FuncAliPayOrderEntity preOrder = lstPreOrders.get(0);
			
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

	/**
	 * 
	 * @description
	 *  	 根据订单号查找支付宝订单
	 * @params
	 *      outTradeNo
	 * @return FuncAliPayOrderEntity
	 * @author mhm
	 * @time 2019年12月17日 下午1:43:04
	 */
	public FuncAliPayOrderEntity finAliPayOrder(String outTradeNo) {
		
		QueryWrapper<FuncAliPayOrderEntity> queryWrapper = new QueryWrapper<FuncAliPayOrderEntity>();
		queryWrapper.eq("OUT_TRADE_NO", outTradeNo);
		List<FuncAliPayOrderEntity> lstOrders = this.list(queryWrapper);
		if(null != lstOrders && lstOrders.size() > 0) {
			return lstOrders.get(0);
		}
		
		return null;
	}
	
}


