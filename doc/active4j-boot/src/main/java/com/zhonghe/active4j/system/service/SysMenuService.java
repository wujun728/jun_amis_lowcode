package com.zhonghe.active4j.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.system.entity.SysMenuEntity;

public interface SysMenuService extends IService<SysMenuEntity> {

	
	/**
	 * 根据指定菜单，获取子级菜单
	 * @param menu
	 * @return
	 */
	public List<SysMenuEntity> getChildMenusByMenu(SysMenuEntity menu);
	
	/**
	 * 删除指定菜单
	 * @param menu
	 */
	public void deleteMenu(SysMenuEntity menu);
	
}
