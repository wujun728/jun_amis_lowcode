package com.jun.plugin.modules.sys.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("sys_role")
public class SysRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("role_id")
	private Long roleId;

	/**
	 * 角色名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 角色描述
	 */
	@TableField("description")
	private String description;

	/**
	 * 状态：1有效; 0无效
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

	/**
	 * 获取角色id
	 *
	 * @return role_id - 角色id
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色id
	 *
	 * @param roleId
	 *            角色id
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId == null ? null : roleId;
	}

	/**
	 * 获取角色名称
	 *
	 * @return name - 角色名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置角色名称
	 *
	 * @param name
	 *            角色名称
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 获取角色描述
	 *
	 * @return description - 角色描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置角色描述
	 *
	 * @param description
	 *            角色描述
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * 获取状态：1有效；0删除
	 *
	 * @return status - 状态：1有效；0删除
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态：1有效；0删除
	 *
	 * @param status
	 *            状态：1有效；0删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}