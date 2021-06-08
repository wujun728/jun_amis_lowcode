package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单编号表
 */
@TableName("mall_order_serial")
public class MallOrderSerial implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId("serial_id")
	private Long serialId;

	/**
	 * 变量代码(order_no + 年月日)
	 */
	@TableField("serial_code")
	private String serialCode;

	/**
	 * 顺序号（自增长）
	 */
	@TableField("serial_no")
	private Integer serialNo;

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

	public Long getSerialId() {
		return serialId;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
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
		return "MallOrderSerial{" + "serialId=" + serialId + ", serialCode=" + serialCode + ", serialNo=" + serialNo + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}
