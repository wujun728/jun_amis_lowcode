package com.nbclass.modules.sys.model;

import java.io.Serializable;

import javax.persistence.Id;

public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long userRoleId;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * @return id
	 */
	public Long getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param id
	 */
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * 获取用户id
	 *
	 * @return user_id - 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 *
	 * @param userId
	 *            用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId == null ? null : userId;
	}

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
}