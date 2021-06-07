package com.nbclass.modules.sys.mapper;

import java.util.Map;
import java.util.Set;

import com.nbclass.common.mapper.MyMapper;
import com.nbclass.modules.sys.model.SysRole;

public interface RoleMapper extends MyMapper<SysRole> {

	/**
	 * 根据参数批量更新状态
	 * 
	 * @param params
	 * @return int
	 */
	int updateStatusBatch(Map<String, Object> params);

	/**
	 * 根据roleId更新角色信息
	 * 
	 * @param params
	 * @return int
	 */
	int updateByRoleId(Map<String, Object> params);
	
	/**
	 * 根据userId查询角色信息
	 * 
	 * @param userId
	 * @return set
	 */
	Set<String> listRoleNameByUserId(Long userId);

}