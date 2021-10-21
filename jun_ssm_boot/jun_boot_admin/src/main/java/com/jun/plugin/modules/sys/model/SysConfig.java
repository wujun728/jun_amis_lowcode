package com.jun.plugin.modules.sys.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 系统配置信息表
 */
@TableName("sys_config")
public class SysConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId("id")
	private Long id;

	/**
	 * key
	 */
	@TableField("sys_key")
	private String sysKey;

	/**
	 * value
	 */
	@TableField("sys_value")
	private String sysValue;

	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;

	/**
	 * 状态 1-有效，0-无效
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSysKey() {
		return sysKey;
	}

	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}

	public String getSysValue() {
		return sysValue;
	}

	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}