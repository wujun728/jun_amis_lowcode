package com.nbclass.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbclass.common.constant.CoreConst;
import com.nbclass.common.util.StringUtils;
import com.nbclass.common.util.UUIDUtil;
import com.nbclass.modules.sys.mapper.MenuMapper;
import com.nbclass.modules.sys.mapper.RoleMenuMapper;
import com.nbclass.modules.sys.model.SysMenu;
import com.nbclass.modules.sys.model.SysUser;
import com.nbclass.modules.sys.service.MenuService;

import tk.mybatis.mapper.entity.Example;

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
		Example example = new Example(SysMenu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", status);
		example.orderBy("orderNum").asc();
		return menuMapper.selectByExample(example);
	}

	@Override
	public List<SysMenu> listAllMenuName(Integer status) {
		Example example = new Example(SysMenu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", status);
		List<Integer> typeList = new ArrayList<>();
		typeList.add(CoreConst.STATUS_INVALID);
		typeList.add(CoreConst.STATUS_VALID);
		criteria.andIn("type", typeList);
		example.orderBy("orderNum").asc();
		return menuMapper.selectByExample(example);
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
		return menuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public SysMenu getSysMenuByMenuId(Long menuId) {
		return menuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public int updateSysMenuByMenuId(SysMenu menu) {
		if (StringUtils.isEmpty(menu.getUrl())) {
			menu.setUrl("#");
		}
		menu.setUpdateTime(new Date());
		return menuMapper.updateByPrimaryKeySelective(menu);
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
			Example example = new Example(SysMenu.class);
			Example.Criteria criteria = example.createCriteria();
			example.setDistinct(true);
			criteria.andEqualTo("status", CoreConst.STATUS_VALID);
			List<Integer> typeList = new ArrayList<>();
			typeList.add(CoreConst.STATUS_VALID);
			typeList.add(CoreConst.STATUS_INVALID);
			criteria.andIn("type", typeList);
			example.orderBy("orderNum").asc();
			menus = menuMapper.selectByExample(example);
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
