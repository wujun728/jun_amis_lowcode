package com.jun.plugin.modules.sys.mapper;

import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.modules.sys.model.SysUserRole;

public interface UserRoleMapper extends BaseMapper<SysUserRole> {

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