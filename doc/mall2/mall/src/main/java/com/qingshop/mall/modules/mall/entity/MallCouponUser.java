package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 优惠券用户使用表
 */
public class MallCouponUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("coupon_user_id")
	private Long couponUserId;

	/**
	 * 用户ID
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 优惠券ID
	 */
	@TableField("coupon_id")
	private Long couponId;

	/**
	 * 使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 使用时间
	 */
	@TableField(value = "used_time", updateStrategy = FieldStrategy.IGNORED)
	private Date usedTime;

	/**
	 * 有效期开始时间
	 */
	@TableField("start_time")
	private Date startTime;

	/**
	 * 有效期截至时间
	 */
	@TableField("end_time")
	private Date endTime;

	/**
	 * 订单ID
	 */
	@TableField(value = "order_id", updateStrategy = FieldStrategy.IGNORED)
	private Long orderId;

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

	public Long getCouponUserId() {
		return couponUserId;
	}

	public void setCouponUserId(Long couponUserId) {
		this.couponUserId = couponUserId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
		return "MallCouponUser{" + "couponUserId=" + couponUserId + ", userId=" + userId + ", couponId=" + couponId + ", status=" + status + ", usedTime=" + usedTime + ", startTime=" + startTime + ", endTime=" + endTime + ", orderId=" + orderId + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "}";
	}
}
