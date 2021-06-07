package com.jun.plugin.modules.sys.model;

import java.io.Serializable;

import javax.persistence.Id;

public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long rolePermId;

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 权限id
	 */
	private Long menuId;

	/**
	 * @return rolePermId
	 */
	public Long getRolePermId() {
		return rolePermId;
	}

	/**
	 * @param rolePermId
	 */
	public void setRolePermId(Long rolePermId) {
		this.rolePermId = rolePermId;
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

	/**
	 * 获取权限id
	 *
	 * @return menu_id - 权限id
	 */
	public Long getMenuId() {
		return menuId;
	}

	/**
	 * 设置权限id
	 *
	 * @param menuId
	 *            权限id
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId == null ? null : menuId;
	}
}