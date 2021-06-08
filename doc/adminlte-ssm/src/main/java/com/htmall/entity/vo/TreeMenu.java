package com.htmall.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.htmall.entity.SysMenu;

public class TreeMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 菜单
	 */
	private SysMenu sysMenu;
	/**
	 * 子菜单
	 */
	private List<TreeMenu> children = new ArrayList<TreeMenu>();

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public List<TreeMenu> getChildren() {
		return children;
	}

	public void setChildren(List<TreeMenu> children) {
		this.children = children;
	}

}
