package com.jun.plugin.modules.sys.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.modules.sys.model.SysUser;

public interface UserMapper extends BaseMapper<SysUser> {

	/**
	 * 根据参数批量修改用户状态
	 * 
	 * @param params
	 * @return int
	 */
	int updateUserStatusByUserIds(Map<String, Object> params);

	/**
	 * 根据角色id查询用户list
	 * 
	 * @param roleIds
	 * @return list
	 */
	List<SysUser> listAllUsersByRoleIds(Long[] roleIds);
}
