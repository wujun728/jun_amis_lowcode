package com.zhonghe.active4j.system.model;

import java.io.Serializable;
import java.util.List;

import com.zhonghe.active4j.system.entity.SysMenuEntity;
import com.zhonghe.active4j.system.entity.SysRoleEntity;

import lombok.Data;

@Data
public class ActiveUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6270912302455345492L;

	private String userName;
	
	private String id;
	
	private String realName;
	
	private String avatar;
	
	private String deptName;
	
	
	//页面菜单集合
	private List<MenuModel> menus;
	
	//角色集合
	private List<SysRoleEntity> roles;
	
	//权限集合
	private List<SysMenuEntity> permissions;

}
