package com.zhonghe.active4j.func.pay.controller;

import java.awt.image.BufferedImage;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.model.PageInfo;
import com.zhonghe.active4j.core.query.QueryUtils;
import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.core.util.QrCodeUtils;
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.core.util.ShiroUtils;
import com.zhonghe.active4j.func.pay.entity.FuncWeixinPayPreOrderEntity;
import com.zhonghe.active4j.func.pay.model.PayConstrant;
import com.zhonghe.active4j.func.pay.service.FuncWeixinPayPreOrderService;
import com.zhonghe.active4j.func.pay.util.OrderNoUtil;
import com.zhonghe.active4j.func.pay.wrapper.FuncWeixinPayPreOrderWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @title FuncWeixinPayPreOrderController.java
 * @description 
		  微信支付管理
 * @time  2019年12月10日 上午10:07:22
 * @author mhm
 * @version 1.0
*/
@Controller
@RequestMapping("/func/pay/wx")
@Slf4j
public class FuncWeixinPayPreOrderController extends BaseController {
	
	@Autowired
	private FuncWeixinPayPreOrderService funcWeixinPayPreOrderService;
	
	/**
	 * 默认跳转页面
	 */
	public static final String prefix_page = "func/pay/wx/";
	
	/**
	 * 
	 * @description
	 *  	跳转到微信付款页面
	 * @params
	 *      model
	 * @return String
	 * @author mhm
	 * @time 2019年12月10日 上午10:18:50 
	 */
	@RequestMapping("/form")
	public String form(Model model) {
		return prefix_page + "weixin_form.html";
	}
	
	/**
	 * 
	 * @description
	 *  	 获取表格数据 树形结构
	 * @return void
	 * @author mhm
	 * @time 2019年12月12日 下午4:50:26
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(FuncWeixinPayPreOrderEntity funcWeixinPayPreOrder, PageInfo<FuncWeixinPayPreOrderEntity> page, HttpServletRequest request, HttpServletResponse response) {
		//拼接查询条件
		QueryWrapper<FuncWeixinPayPreOrderEntity> queryWrapper = QueryUtils.installQueryWrapper(funcWeixinPayPreOrder, request.getParameterMap());
		
		//执行查询
		IPage<FuncWeixinPayPreOrderEntity> lstResult = funcWeixinPayPreOrderService.page(page.getPageEntity(), queryWrapper);
		
		//结果处理,直接写到客户端
		ResponseUtil.write(response, new FuncWeixinPayPreOrderWrapper(lstResult).wrap());
	}
	
	/**
	 * 
	 * @description
	 *  	生成订单号
	 * @return AjaxJson
	 * @author mhm
	 * @time 2019年12月12日 上午10:46:19
	 */
	@RequestMapping("/getOrderNo")
	@ResponseBody
	public AjaxJson getOrderNo(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String userName = ShiroUtils.getSessionUserName();
		try {
			//构建预支付实体
			String outTradeNo = OrderNoUtil.getOrderNo();
			
			j.setObj(outTradeNo);
		}catch(Exception e) {
			log.error("用户{}充值生成orderNo报错，错误信息{}", userName, e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到微信二维码付款页面
	 * @return String
	 * @author mhm
	 * @time 2019年12月11日 上午10:50:46
	 */
	@RequestMapping("/wxpay")
	public String wxPay(Model model, String money, String orderNo) {
		
		//支付金额
		Double m = Double.parseDouble(money);
		
		AjaxJson j = funcWeixinPayPreOrderService.doWeixinPay(orderNo, m);
		
		if(j.isSuccess()) {
			//成功生成微信支付订单，并返回了支付二维码
			//支付时间
			String payDate = DateUtils.getDate2Str(new Date());
			String qrcode = (String)j.getObj();
			
			model.addAttribute("company", "江苏众禾");
			model.addAttribute("orderNo", orderNo);
			model.addAttribute("name", "演示微信支付");
			model.addAttribute("payDate", payDate);
			model.addAttribute("money", m);
			model.addAttribute("qrcode", qrcode);
			
			return prefix_page + "weixin_pay.html";
		}else {
			
			model.addAttribute("errmsg", j.getMsg());
			return prefix_page + "weixin_pay_failresult.html";
		}
	
	}
	
	/**
	 * 根据微信支付返回结果，生成二维码
	 * @param key
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getQrcode")
	@ResponseBody
	public void getQrcode(String key, HttpServletRequest request, HttpServletResponse response) {
		String userNo = ShiroUtils.getSessionUserName();
		try {
			
			if(StringUtils.isNotEmpty(key)) {
				
				BufferedImage image = QrCodeUtils.createImage(key);
				
				// 输出图象到页面
				ImageIO.write(image, QrCodeUtils.FORMAT_NAME, response.getOutputStream());
			}
			
		}catch(Exception e) {
			log.error("用户{}充值生成二维码报错，错误信息{}", userNo, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description
	 *  	微信支付获取微信订单支付状态
	 * @params
	 *      orderNo
	 *      request
	 *      response
	 * @return ResultJson
	 * @author mhm
	 * @time 2019年12月11日 下午5:26:42
	 */
	@RequestMapping("/getWxOrderStatus")
	@ResponseBody
	public AjaxJson getWxOrderStatus(String orderNo, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			String userId = ShiroUtils.getSessionUserId();
			String payStatus = funcWeixinPayPreOrderService.getWeixinPayStatus(userId, orderNo);
			//必须是有状态的支付订单 才返回
			if(StringUtils.equals(payStatus, PayConstrant.weixin_pay_status_success) 
					|| StringUtils.equals(payStatus, PayConstrant.weixin_pay_status_fail)) {
				j.setSuccess(true);
				j.setObj(payStatus);
			}else {
				j.setSuccess(false);
			}
		}catch(Exception e) {
			j.setSuccess(false);
			j.setMsg("sorry, 系统遇到问题, 请稍后再试");
			log.error("微信支付订单:{}, 获取状态错误，错误信息:{}", orderNo, e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到微信支付结果页面
	 * @params
	 *      payStatus, model
	 * @return String
	 * @author mhm
	 * @time 2019年12月12日 上午11:27:31
	 */
	@RequestMapping("/getWxPayReuslt")
	public String getWxPayReuslt(String payStatus, String orderNo, String payDate, String money, Model model) {
		
		if(StringUtils.equals(payStatus, PayConstrant.weixin_pay_status_success)) {
			model.addAttribute("payMsg", "支付成功");
			model.addAttribute("orderNo", orderNo);
			model.addAttribute("name", "演示微信支付");
			model.addAttribute("payDate", payDate);
			model.addAttribute("money", money);
			
		}else {
			model.addAttribute("payMsg", "支付失败");
			return prefix_page + "weixin_pay_fail_result.html";
		}
		
		return prefix_page + "weixin_pay_result.html";
	}
}
