package com.htmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htmall.entity.SysMenu;
import com.htmall.entity.vo.TreeMenu;
import com.htmall.entity.vo.TreeMenuAllowAccess;
import com.htmall.mapper.SysMenuMapper;
import com.htmall.mapper.SysRoleMenuMapper;
import com.htmall.service.ISysMenuService;

/**
 *
 * SysMenu 表数据服务层接口实现类
 *
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
	/**
	 * 菜单服务
	 */
	@Autowired
	private SysMenuMapper sysMenuMapper;
	/**
	 * 角色菜单关系服务
	 */
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Cacheable(value = "permissionCache", key = "#uid")
	@Override
	public List<String> selectMenuIdsByuserId(String uid) {
		return sysMenuMapper.selectMenuIdsByuserId(uid);
	}

	@Cacheable(value = "menuCache", key = "#uid")
	@Override
	public List<TreeMenu> selectTreeMenuByUserId(String uid) {
		/**
		 * 当前用户二级菜单权限
		 */
		List<String> menuIds = sysRoleMenuMapper.selectRoleMenuIdsByUserId(uid);
		return selectTreeMenuByMenuIdsAndPid(menuIds, "0");
	}

	@Override
	public List<TreeMenu> selectTreeMenuByMenuIdsAndPid(final List<String> menuIds, String pid) {
		QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
		ew.eq("pid", pid);
		ew.orderByAsc("sort");
		if (!menuIds.isEmpty()) {
			ew.in("id", menuIds);
		}
		List<SysMenu> sysMenus = this.list(ew);
		List<TreeMenu> treeMenus = new ArrayList<TreeMenu>();
		for (SysMenu sysMenu : sysMenus) {
			TreeMenu treeMenu = new TreeMenu();
			treeMenu.setSysMenu(sysMenu);
			if (sysMenu.getDeep() < 2) {
				treeMenu.setChildren(selectTreeMenuByMenuIdsAndPid(menuIds, sysMenu.getId()));
			}
			treeMenus.add(treeMenu);
		}
		return treeMenus;
	}

	@Override
	public List<TreeMenuAllowAccess> selectTreeMenuAllowAccessByMenuIdsAndPid(final List<String> menuIds, String pid) {
		QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();
		ew.eq("pid", pid);
		ew.orderByAsc("sort");
		List<SysMenu> sysMenus = this.list(ew);

		List<TreeMenuAllowAccess> treeMenuAllowAccesss = new ArrayList<TreeMenuAllowAccess>();
		for (SysMenu sysMenu : sysMenus) {
			TreeMenuAllowAccess treeMenuAllowAccess = new TreeMenuAllowAccess();
			treeMenuAllowAccess.setSysMenu(sysMenu);
			/**
			 * 是否有权限
			 */
			if (menuIds.contains(sysMenu.getId())) {
				treeMenuAllowAccess.setAllowAccess(true);
			}
			/**
			 * 子节点
			 */
			if (sysMenu.getDeep() < 3) {
				treeMenuAllowAccess.setChildren(selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds, sysMenu.getId()));
			}
			treeMenuAllowAccesss.add(treeMenuAllowAccess);
		}
		return treeMenuAllowAccesss;
	}

}