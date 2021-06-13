package com.qingshop.mall.modules.mall.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * 店家优惠券
 */
public class MallCoupon implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 活动ID
	 */
	@TableId("coupon_id")
	private Long couponId;

	/**
	 * 活动名称
	 */
	@TableField("coupon_name")
	private String couponName;

	/**
	 * 活动条件值
	 */
	@TableField("condition_num")
	private Integer conditionNum;

	/**
	 * 优惠金额
	 */
	@TableField("discount")
	private BigDecimal discount;

	/**
	 * 优惠券数量，如果是0，则是无限量
	 */
	@TableField("total_num")
	private Integer totalNum;

	/**
	 * 优惠券剩余数量
	 */
	@TableField("remain_num")
	private Integer remainNum;

	/**
	 * 1为全场通用，2未类别优惠，3位商品优惠
	 */
	@TableField("range_type")
	private Integer rangeType;

	/**
	 * 范围关系ID
	 */
	@TableField("range_relation_id")
	private Long rangeRelationId;

	/**
	 * 作用范围名称
	 */
	@TableField("range_relation_name")
	private String rangeRelationName;

	/**
	 * 优惠券状态，1是正常；0是过期; 2是下架
	 */
	@TableField("activity_status")
	private Integer activityStatus;

	/**
	 * 0是有效天数days；1是start_time和end_time有效期；
	 */
	@TableField("time_type")
	private Integer timeType;

	/**
	 * 基于领取时间的有效天数days。
	 */
	@TableField("days")
	private Integer days;

	/**
	 * 开始时间
	 */
	@TableField("start_time")
	private Date startTime;

	/**
	 * 结束时间
	 */
	@TableField("end_time")
	private Date endTime;

	/**
	 * 下架时间
	 */
	@TableField("del_time")
	private Date delTime;

	/**
	 * 描述
	 */
	@TableField("activity_desc")
	private String activityDesc;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private Date updateTime;

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getConditionNum() {
		return conditionNum;
	}

	public void setConditionNum(Integer conditionNum) {
		this.conditionNum = conditionNum;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

	public Integer getRangeType() {
		return rangeType;
	}

	public void setRangeType(Integer rangeType) {
		this.rangeType = rangeType;
	}

	public Long getRangeRelationId() {
		return rangeRelationId;
	}

	public void setRangeRelationId(Long rangeRelationId) {
		this.rangeRelationId = rangeRelationId;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
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

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
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

	public String getRangeRelationName() {
		return rangeRelationName;
	}

	public void setRangeRelationName(String rangeRelationName) {
		this.rangeRelationName = rangeRelationName;
	}

	@Override
	public String toString() {
		return "MallCoupon{" + "couponId=" + couponId + ", couponName=" + couponName + ", conditionNum=" + conditionNum + ", discount=" + discount + ", totalNum=" + totalNum + ", remainNum=" + remainNum + ", rangeType=" + rangeType + ", rangeRelationId=" + rangeRelationId + ", activityStatus="
				+ activityStatus + ", timeType=" + timeType + ", days=" + days + ", startTime=" + startTime + ", endTime=" + endTime + ", delTime=" + delTime + ", activityDesc=" + activityDesc + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}
