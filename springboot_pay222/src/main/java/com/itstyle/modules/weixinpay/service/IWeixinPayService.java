package com.itstyle.modules.weixinpay.service;

import com.itstyle.common.model.Product;


public interface IWeixinPayService {
	/**
	 * 微信支付下单(模式二)
	 * 扫码支付 还有模式一 适合固定商品ID 有兴趣的同学可以自行研究
	 * @Author  科帮网
	 * @param product
	 * @return  String
	 * @Date	2017年7月31日
	 * 更新日志
	 * 2017年7月31日  科帮网 首次创建
	 *
	 */
	String weixinPay(Product product);
    /**
     * 微信支付退款
     * @Author  科帮网
     * @param product
     * @return  String
     * @Date	2017年7月31日
     * 更新日志
     * 2017年7月31日  科帮网 首次创建
     *
     */
	String weixinRefund(Product product);
	/**
	 * 关闭订单
	 * @Author  科帮网
	 * @param product
	 * @return  String
	 * @Date	2017年7月31日
	 * 更新日志
	 * 2017年7月31日  科帮网 首次创建
	 *
	 */
	String weixinCloseorder(Product product);
	/**
	 * 下载微信账单
	 * @Author  科帮网  void
	 * @Date	2017年7月31日
	 * 更新日志
	 * 2017年7月31日  科帮网 首次创建
	 *
	 */
	void saveBill();
    /**
     * 微信手机支付返回一个url地址
     * @Author  科帮网
     * @param product
     * @return  String
     * @Date	2017年7月31日
     * 更新日志
     * 2017年7月31日  科帮网 首次创建
     *
     */
	String weixinPayMobile(Product product);
	
}
