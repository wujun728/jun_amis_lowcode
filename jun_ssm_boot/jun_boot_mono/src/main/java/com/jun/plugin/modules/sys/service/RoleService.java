package com.jun.plugin.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.sys.model.SysMenu;
import com.jun.plugin.modules.sys.model.SysRole;
import com.jun.plugin.modules.sys.model.SysUser;

public interface RoleService {

	/**
	 * 根据条件查询角色列表
	 * 
	 * @param role
	 * @return list
	 */
	List<SysRole> listRoles(SysRole role);

	/**
	 * 插入角色
	 * 
	 * @param role
	 * @return int
	 */
	int save(SysRole role);

	/**
	 * 批量更新状态
	 * 
	 * @param roleIds
	 * @param status
	 * @return int
	 */
	int updateStatusBatch(Long[] roleIds, Integer status);

	/**
	 * 根据id查询角色
	 * 
	 * @param id
	 * @return role
	 */
	SysRole getSysRoleById(Long id);

	/**
	 * 根据角色id更新角色信息
	 * 
	 * @param role
	 * @return int
	 */
	int updateByRoleId(SysRole role);

	/**
	 * 根据角色id查询权限集合
	 * 
	 * @param roleId
	 * @return list
	 */
	List<SysMenu> listMenusByRoleId(Long roleId);

	/**
	 * 根据角色id保存分配权限
	 * 
	 * @param roleId
	 * @param menuIdsList
	 * @return list
	 */
	ResponseVo<?> addAssignMenu(Long roleId, List<Long> menuIdsList);

	/**
	 * 根据角色id下的所有用户
	 * 
	 * @param roleIds
	 * @return list
	 */
	List<SysUser> listAllUsersByRoleIds(Long[] roleIds);

	/**
	 * 根据用户id查询角色集合
	 * 
	 * @param userId
	 * @return set
	 */
	Set<String> listRoleIdsByUserId(Long userId);
	
	/**
	 * 根据角色id查询用户集合
	 * 
	 * @param roleId
	 * @return set
	 */
	Set<String> listUserIdsByRoleId(Long roleId);
	
	/**
	 * 取消分配用户
	 * 
	 * @param roleId
	 * @return set
	 */
	ResponseVo<?> cancelAssign(Long roleId, Long userId);
	
	/**
	 * 分配用户
	 * 
	 * @param roleId
	 * @return set
	 */
	ResponseVo<?> assignUser(Long roleId, Long userId);
	
	/**
	 * 获取角色
	 * 
	 * @param userId
	 * @return set
	 */
	Set<String> listRoleNameByUserId(Long userId);
}
