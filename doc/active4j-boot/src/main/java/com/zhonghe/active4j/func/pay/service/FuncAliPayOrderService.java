package com.zhonghe.active4j.func.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.func.pay.entity.FuncAliPayOrderEntity;

/**
 * @title FuncAliPayOrderService.java
 * @description 
 * @time  2019年12月16日 下午1:58:50
 * @author mhm
 * @version 1.0
*/
public interface FuncAliPayOrderService extends IService<FuncAliPayOrderEntity> {

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
	public AjaxJson doAliPay(String outTradeNo, double money);
	
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
	public String getAliPayStatus(String userId, String orderNo);
	
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
	public FuncAliPayOrderEntity finAliPayOrder(String outTradeNo);
	
}
