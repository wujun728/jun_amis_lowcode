package com.zhonghe.active4j.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.system.dao.SysMenuDao;
import com.zhonghe.active4j.system.entity.SysMenuEntity;
import com.zhonghe.active4j.system.entity.SysRoleMenuEntity;
import com.zhonghe.active4j.system.service.SysMenuService;
import com.zhonghe.active4j.system.service.SysRoleMenuService;


/**
 *   菜单service类
 * @author teli_
 *
 */
@Service("sysMenuService")
@Transactional
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 根据指定菜单，获取子级菜单
	 * @param menu
	 * @return
	 */
	public List<SysMenuEntity> getChildMenusByMenu(SysMenuEntity menu){
		QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<SysMenuEntity>();
		queryWrapper.eq("PARENT_ID", menu.getId());
		return this.list(queryWrapper);
	}

	
	/**
	 * 删除指定菜单
	 * @param menu
	 */
	public void deleteMenu(SysMenuEntity menu) {
		QueryWrapper<SysRoleMenuEntity> queryWrapper = new QueryWrapper<SysRoleMenuEntity>();
		queryWrapper.eq("MENU_ID", menu.getId());
		sysRoleMenuService.remove(queryWrapper);
		
		this.removeById(menu.getId());
	}
}
