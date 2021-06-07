package com.zhonghe.active4j.func.pay.controller;

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
import com.zhonghe.active4j.core.util.ResponseUtil;
import com.zhonghe.active4j.core.util.ShiroUtils;
import com.zhonghe.active4j.func.pay.entity.FuncAliPayOrderEntity;
import com.zhonghe.active4j.func.pay.model.PayConstrant;
import com.zhonghe.active4j.func.pay.service.FuncAliPayOrderService;
import com.zhonghe.active4j.func.pay.wrapper.FuncAliPayOrderWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @title FuncAliPayOrderController.java
 * @description 
		   支付宝支付管理
 * @time  2019年12月16日 上午10:38:16
 * @author mhm
 * @version 1.0
*/
@Controller
@RequestMapping("/func/pay/ali")
@Slf4j
public class FuncAliPayOrderController extends BaseController {

	@Autowired
	private FuncAliPayOrderService funcAliPayOrderService;
	
	/**
	 * 默认跳转页面
	 */
	public static final String prefix_page = "func/pay/ali/";
	
	/**
	 * 
	 * @description
	 *  	跳转到支付宝付款页面
	 * @params
	 *      model
	 * @return String
	 * @author mhm
	 * @time 2019年12月10日 上午10:18:50
	 */
	@RequestMapping("/form")
	public String form(Model model) {
		return prefix_page + "ali_form.html";
	}
	
	/**
	 * 
	 * @description
	 *  	 获取表格数据 树形结构
	 * @params
	 *      funcAliPayOrderEntity
	 *      page
	 *      request
	 *      response
	 * @return void
	 * @author mhm
	 * @time 2019年12月16日 下午2:04:28
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public void datagrid(FuncAliPayOrderEntity funcAliPayOrderEntity, PageInfo<FuncAliPayOrderEntity> page, HttpServletRequest request, HttpServletResponse response) {
		try {
			//拼接查询条件
			QueryWrapper<FuncAliPayOrderEntity> queryWrapper = QueryUtils.installQueryWrapper(funcAliPayOrderEntity, request.getParameterMap());
			
			//执行查询
			IPage<FuncAliPayOrderEntity> lstResult = funcAliPayOrderService.page(page.getPageEntity(), queryWrapper);
			//结果处理,直接写到客户端
			ResponseUtil.write(response, new FuncAliPayOrderWrapper(lstResult).wrap());
		}catch(Exception e) {
			e.printStackTrace();
			log.error("查询支付宝下单数据报错，错误信息:" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @description
	 *  	跳转到支付宝二维码付款页面
	 * @return String
	 * @author mhm
	 * @time 2019年12月11日 上午10:50:46
	 */
	@RequestMapping("/alipay")
	public String aliPay(Model model, String money, String orderNo) {
		
		//支付金额
		Double m = Double.parseDouble(money);
		
		AjaxJson j = funcAliPayOrderService.doAliPay(orderNo, m);
		
		if(j.isSuccess()) {
			
			model.addAttribute("form", j.getObj());
			
			return prefix_page + "ali_pay.html";
		}else {
			
			model.addAttribute("errmsg", j.getMsg());
			return prefix_page + "ali_pay_failresult.html";
		}
	
	}
	
	/**
	 * 
	 * @description
	 *  	支付宝支付获取支付宝订单支付状态
	 * @params
	 *      orderNo
	 *      request
	 *      response
	 * @return AjaxJson
	 * @author mhm
	 * @time 2019年12月16日 下午4:11:49
	 */
	@RequestMapping("/getOrderStatus")
	@ResponseBody
	public AjaxJson getOrderStatus(String orderNo, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			String userId = ShiroUtils.getSessionUserId();
			String payStatus = funcAliPayOrderService.getAliPayStatus(userId, orderNo);
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
	
}
