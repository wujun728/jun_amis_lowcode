package com.jun.plugin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.system.domain.Role;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/19
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 根据角色ID删除角色和菜单之间的关系
	 *
	 * @param id
	 */
	void deleteRoleMenuByRid(Serializable id);

	/**
	 * 根据菜单ID删除角色和菜单之间的关系
	 *
	 * @param id
	 */
	void deleteRoleMenuByMid(Serializable id);

	/**
	 * 根据角色ID删除角色和用户之间的关系
	 *
	 * @param id
	 */
	void deleteRoleUserByRid(Serializable id);

	/**
	 * 根据用户ID删除角色和用户之间的关系
	 *
	 * @param id
	 */
	void deleteRoleUserByUid(Serializable id);

	List<Integer> queryMenuIdsByRid(@Param("id") Integer id);

	void insertRoleMenu(@Param("rid") Integer rid, @Param("mids") Integer[] mids);

	List<Integer> queryRoleIdsByUserId(Integer userId);

	void insertUserRole(@Param("uid") Integer uid, @Param("rids") Integer[] rids);

	List<Integer> queryMenuIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);
}