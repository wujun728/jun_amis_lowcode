package com.nbclass.modules.sys.mapper;

import java.util.Set;

import com.nbclass.common.mapper.MyMapper;
import com.nbclass.modules.sys.model.SysUserRole;

public interface UserRoleMapper extends MyMapper<SysUserRole> {

	/**
	 * 根据用户id查询角色id集合
	 * 
	 * @param userId
	 *            用户id
	 * @return set
	 */
	Set<String> listRoleIdsByUserId(Long userId);

	/**
	 * 根据角色id查询用户id集合
	 * 
	 * @param roleId
	 *            用户id
	 * @return set
	 */
	Set<String> listUserIdsByRoleId(Long roleId);
}