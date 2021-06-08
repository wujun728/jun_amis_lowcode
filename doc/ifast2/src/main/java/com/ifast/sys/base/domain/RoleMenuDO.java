package com.ifast.sys.base.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年3月23日 | Aron</small>
 */
@TableName("sys_role_menu")
public class RoleMenuDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long              id;
	@TableField("role_id")
	private Long              roleId;
	@TableField("menu_id")
	private Long              menuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenuDO{" + "id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + '}';
	}
}
