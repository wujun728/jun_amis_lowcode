package com.jun.plugin.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.jun.plugin.modules.sys.model.SysMenu;
import com.jun.plugin.modules.sys.model.SysUser;

/**
 * <p>
 * ClassName: MenuService
 * </p>
 * <p>
 * Description: (这里用一句话描述这个类的作用)
 * </p>
 * <p>
 * Company: 爱用科技有限公司
 * </p>
 * 
 * @date: 2020年7月15日
 * @version v1.0
 * @since JDK 1.8
 */
public interface MenuService {

	/**
	 * 根据用户id查询权限集合
	 * 
	 * @param userId
	 * @return set
	 */
	Set<String> listPermsByUserId(Long userId);

	/**
	 * 查询全部权限
	 * 
	 * @param status
	 * @return list
	 */
	List<SysMenu> listAllPermsByStatus(Integer status);

	/**
	 * 查询全部权限菜单名
	 *
	 * @param status
	 * @return
	 */
	List<SysMenu> listAllMenuName(Integer status);

	/**
	 * 插入权限
	 * 
	 * @param menu
	 * @return int
	 */
	int save(SysMenu menu);

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @param status
	 * @return int
	 */
	int deleteMenuByMenuId(Long menuId);

	/**
	 * 根据权限id查询权限
	 * 
	 * @param menuId
	 * @return menu
	 */
	SysMenu getSysMenuByMenuId(Long menuId);

	/**
	 * 更新权限
	 * 
	 * @param menu
	 * @return int
	 */
	int updateSysMenuByMenuId(SysMenu menu);

	/**
	 * 根据登录用户查询用户菜单
	 *
	 * @param user
	 * @return
	 */
	List<SysMenu> listMenusByUser(SysUser user);

	/**
	 * 查询子菜单数量
	 * 
	 * @param menuId
	 * @return int
	 */
	int countMenuByParentId(Long menuId);

	/**
	 * 菜单使用数量
	 * 
	 * @param menuId
	 * @return int
	 */
	int countRoleMenuByMenuId(Long menuId);

}
