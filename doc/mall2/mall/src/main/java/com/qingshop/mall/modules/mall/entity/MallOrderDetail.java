package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单商品表
 */
@TableName("mall_order_detail")
public class MallOrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("order_detail_id")
	private Long orderDetailId;

	/**
	 * 订单表的订单ID
	 */
	@TableField("order_id")
	private Long orderId;

	/**
	 * 商品表的商品ID
	 */
	@TableField("goods_id")
	private Long goodsId;

	/**
	 * 商品名称
	 */
	@TableField("goods_name")
	private String goodsName;

	/**
	 * 商品编号
	 */
	@TableField("goods_sn")
	private String goodsSn;

	/**
	 * 商品货品ID
	 */
	@TableField("sku_detail_id")
	private Long skuDetailId;

	/**
	 * 购买数量
	 */
	private Integer number;

	/**
	 * 商品销售价
	 */
	@TableField("good_price")
	private BigDecimal goodPrice;

	/**
	 * 商品货品的规格
	 */
	private String specifications;

	/**
	 * 商品图片
	 */
	@TableField("pic_url")
	private String picUrl;

	/**
	 * -1：超期不能评价；0：可以评价；其他值：comment表的评论ID
	 */
	private Long comment;

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

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSn() {
		return goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}

	public Long getSkuDetailId() {
		return skuDetailId;
	}

	public void setSkuDetailId(Long skuDetailId) {
		this.skuDetailId = skuDetailId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public BigDecimal getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(BigDecimal goodPrice) {
		this.goodPrice = goodPrice;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Long getComment() {
		return comment;
	}

	public void setComment(Long comment) {
		this.comment = comment;
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

	@Override
	public String toString() {
		return "MallOrderDetail{" + "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsSn=" + goodsSn + ", skuDetailId=" + skuDetailId + ", number=" + number + ", goodPrice=" + goodPrice + ", specifications=" + specifications
				+ ", picUrl=" + picUrl + ", comment=" + comment + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}
