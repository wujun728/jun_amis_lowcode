package com.jun.plugin.modules.sys.mapper;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.modules.sys.model.SysMenu;

public interface MenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 根据用户id查询权限集合
	 * 
	 * @param userId
	 *            状态
	 * @return set
	 */
	Set<String> listPermsByUserId(Long userId);

	/**
	 * 根据角色id查询权限
	 * 
	 * @param id
	 *            角色id
	 * @return the list
	 */
	List<SysMenu> listMenusByRoleId(Long id);

	/**
	 * 根据用户id查询菜单
	 * 
	 * @param userId
	 *            用户id
	 * @return the list
	 */
	List<SysMenu> listMenuByUserId(Long userId);

	/**
	 * 根据menuId查询子菜单数量
	 * 
	 * @param menuId
	 *            权限id
	 * @return int
	 */
	int countMenuByParentId(Long menuId);

}