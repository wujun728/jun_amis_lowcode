package com.jun.plugin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.modules.sys.mapper.MenuMapper;
import com.jun.plugin.modules.sys.mapper.RoleMenuMapper;
import com.jun.plugin.modules.sys.model.SysMenu;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public Set<String> listPermsByUserId(Long userId) {
		return menuMapper.listPermsByUserId(userId);
	}

	@Override
	public List<SysMenu> listAllPermsByStatus(Integer status) {
		QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>();
		queryWrapper.eq("status", status);
		queryWrapper.orderByAsc("order_num");
		return menuMapper.selectList(queryWrapper);
	}

	@Override
	public List<SysMenu> listAllMenuName(Integer status) {
		QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>();
		queryWrapper.eq("status", status);
		List<Integer> typeList = new ArrayList<>();
		typeList.add(CoreConst.STATUS_INVALID);
		typeList.add(CoreConst.STATUS_VALID);
		queryWrapper.in("type", typeList);
		queryWrapper.orderByAsc("order_num");
		return menuMapper.selectList(queryWrapper);
	}

	@Override
	public int save(SysMenu menu) {
		Date date = new Date();
		if (StringUtils.isEmpty(menu.getUrl())) {
			menu.setUrl("#");
		}
		menu.setMenuId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
		menu.setStatus(CoreConst.STATUS_VALID);
		menu.setCreateTime(date);
		menu.setUpdateTime(date);
		return menuMapper.insert(menu);
	}

	@Override
	public int deleteMenuByMenuId(Long menuId) {
		return menuMapper.deleteById(menuId);
	}

	@Override
	public SysMenu getSysMenuByMenuId(Long menuId) {
		return menuMapper.selectById(menuId);
	}

	@Override
	public int updateSysMenuByMenuId(SysMenu menu) {
		if (StringUtils.isEmpty(menu.getUrl())) {
			menu.setUrl("#");
		}
		menu.setUpdateTime(new Date());
		return menuMapper.updateById(menu);
	}

	@Override
	public int countMenuByParentId(Long menuId) {
		return menuMapper.countMenuByParentId(menuId);
	}
	
	@Override
	public int countRoleMenuByMenuId(Long menuId) {
		return roleMenuMapper.countRoleMenuByMenuId(menuId);
	}

	@Override
	public List<SysMenu> listMenusByUser(SysUser user) {
		// 超级管理员
		List<SysMenu> menus = new LinkedList<SysMenu>();
		if (user.isAdmin()) {
			QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>();
			queryWrapper.eq("status", CoreConst.STATUS_VALID);
			List<Integer> typeList = new ArrayList<>();
			typeList.add(CoreConst.STATUS_VALID);
			typeList.add(CoreConst.STATUS_INVALID);
			queryWrapper.in("type", typeList);
			queryWrapper.orderByAsc("order_num");
			menus = menuMapper.selectList(queryWrapper);
		} else {
			menus = menuMapper.listMenuByUserId(user.getUserId());
		}
		return getChildPerms(menus, 0);
	}

	/**
	 * 根据父节点的ID获取所有子节点
	 * 
	 * @param list
	 *            分类表
	 * @param parentId
	 *            传入的父节点ID
	 * @return String
	 */
	public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
		List<SysMenu> returnList = new ArrayList<SysMenu>();
		for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();) {
			SysMenu t = (SysMenu) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId() == parentId) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}

	/**
	 * 递归列表
	 * 
	 * @param list
	 * @param t
	 */
	private void recursionFn(List<SysMenu> list, SysMenu t) {
		// 得到子节点列表
		List<SysMenu> childList = getChildList(list, t);
		t.setChildren(childList);
		for (SysMenu tChild : childList) {
			if (hasChild(list, tChild)) {
				// 判断是否有子节点
				Iterator<SysMenu> it = childList.iterator();
				while (it.hasNext()) {
					SysMenu n = (SysMenu) it.next();
					recursionFn(list, n);
				}
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
		List<SysMenu> tlist = new ArrayList<SysMenu>();
		Iterator<SysMenu> it = list.iterator();
		while (it.hasNext()) {
			SysMenu n = (SysMenu) it.next();
			if (n.getParentId().longValue() == t.getMenuId().longValue()) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<SysMenu> list, SysMenu t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}

}
