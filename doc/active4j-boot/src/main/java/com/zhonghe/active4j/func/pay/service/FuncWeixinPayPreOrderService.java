package com.zhonghe.active4j.func.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.func.pay.entity.FuncWeixinPayPreOrderEntity;

/**
 * @title WeixinPayPreOrderService.java
 * @description 
		  微信预下单列表
 * @time  2019年12月10日 下午4:34:19
 * @author mhm
 * @version 1.0
*/
public interface FuncWeixinPayPreOrderService extends IService<FuncWeixinPayPreOrderEntity>{

	
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
	public AjaxJson doWeixinPay(String orderNo, Double money);
	
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
	public FuncWeixinPayPreOrderEntity findOrder(String outTradeNo);
	
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
	public String getWeixinPayStatus(String userId, String outTradeNo);

}
