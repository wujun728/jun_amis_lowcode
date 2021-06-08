package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单表
 */
@TableName("mall_order")
public class MallOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("order_id")
	private Long orderId;

	/**
	 * 用户表的用户ID
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 订单编号
	 */
	@TableField("order_sn")
	private Long orderSn;

	/**
	 * 订单状态（1-正常 0-取消 2-完成，默认为1）
	 */
	@TableField("order_status")
	private Integer orderStatus;

	/**
	 * 订单支付状态（0-未支付 1-已支付）
	 */
	@TableField("pay_status")
	private Integer payStatus;

	/**
	 * 订单配送状态（0-未发货 1-已发货 2-已收货）
	 */
	@TableField("ship_status")
	private Integer shipStatus;

	/**
	 * 收货人名称
	 */
	private String consignee;

	/**
	 * 收货人手机号
	 */
	private String mobile;

	/**
	 * 收货具体地址
	 */
	private String address;

	/**
	 * 用户订单留言
	 */
	private String message;

	/**
	 * 订单产品总价格(未优惠的总价)
	 */
	@TableField("total_price")
	private BigDecimal totalPrice;

	/**
	 * 配送价格
	 */
	@TableField("ship_price")
	private BigDecimal shipPrice;

	/**
	 * 优惠价格
	 */
	@TableField("coupon_price")
	private BigDecimal couponPrice;

	/**
	 * 实际支付价格
	 */
	@TableField("order_price")
	private BigDecimal orderPrice;

	/**
	 * 微信付款编号
	 */
	@TableField("pay_id")
	private String payId;

	/**
	 * 微信付款时间
	 */
	@TableField("pay_time")
	private Date payTime;

	/**
	 * 发货编号
	 */
	@TableField("ship_sn")
	private String shipSn;

	/**
	 * 发货快递公司
	 */
	@TableField("ship_channel")
	private String shipChannel;

	/**
	 * 发货开始时间
	 */
	@TableField("ship_time")
	private Date shipTime;

	/**
	 * 用户确认收货时间
	 */
	@TableField("confirm_time")
	private Date confirmTime;

	/**
	 * 订单关闭时间
	 */
	@TableField("end_time")
	private Date endTime;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	@TableField(exist = false)
	private List<MallOrderDetail> goods;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(Long orderSn) {
		this.orderSn = orderSn;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(Integer shipStatus) {
		this.shipStatus = shipStatus;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getShipPrice() {
		return shipPrice;
	}

	public void setShipPrice(BigDecimal shipPrice) {
		this.shipPrice = shipPrice;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getShipSn() {
		return shipSn;
	}

	public void setShipSn(String shipSn) {
		this.shipSn = shipSn;
	}

	public String getShipChannel() {
		return shipChannel;
	}

	public void setShipChannel(String shipChannel) {
		this.shipChannel = shipChannel;
	}

	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<MallOrderDetail> getGoods() {
		return goods;
	}

	public void setGoods(List<MallOrderDetail> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "MallOrder{" + "orderId=" + orderId + ", userId=" + userId + ", orderSn=" + orderSn + ", orderStatus=" + orderStatus + ", payStatus=" + payStatus + ", shipStatus=" + shipStatus + ", consignee=" + consignee + ", mobile=" + mobile + ", address=" + address + ", message=" + message
				+ ", totalPrice=" + totalPrice + ", shipPrice=" + shipPrice + ", couponPrice=" + couponPrice + ", orderPrice=" + orderPrice + ", payId=" + payId + ", payTime=" + payTime + ", shipSn=" + shipSn + ", shipChannel=" + shipChannel + ", shipTime=" + shipTime + ", confirmTime="
				+ confirmTime + ", endTime=" + endTime + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}
