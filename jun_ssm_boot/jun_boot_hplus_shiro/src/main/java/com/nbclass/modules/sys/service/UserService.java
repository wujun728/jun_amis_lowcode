package com.nbclass.modules.sys.service;

import java.util.List;

import com.nbclass.common.vo.ResponseVo;
import com.nbclass.modules.sys.model.SysUser;

public interface UserService {

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return user
	 */
	SysUser getUserByUsername(String username);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return int
	 */
	int register(SysUser user);

	/**
	 * 更新最后登录时间
	 * 
	 * @param user
	 */
	void updateLastLoginTimeByUser(SysUser sysUser);

	/**
	 * 根据条件查询用户列表
	 * 
	 * @param user
	 * @return list
	 */
	List<SysUser> listUsers(SysUser user);

	/**
	 * 根据用户id查询用户
	 * 
	 * @param userId
	 * @return user
	 */
	SysUser getUserByUserId(Long userId);

	/**
	 * 根据userId更新用户信息
	 * 
	 * @param user
	 * @return int
	 */
	int updateUserByUserId(SysUser sysUser);

	/**
	 * 根据用户id集合批量更新用户状态
	 * 
	 * @param userIds
	 * @param status
	 * @return int
	 */
	int updateUserStatusByUserIds(List<String> userIds, Integer status);

	/**
	 * 根据用户id分配角色集合
	 * 
	 * @param userId
	 * @param roleIds
	 * @return int
	 */
	ResponseVo<?> addAssignRole(Long userId, Long[] roleIds);

	/**
	 * 导入用户数据
	 *
	 * @param userList
	 * @param updateSupport
	 * @param operName
	 * @return
	 */
	String importUser(List<SysUser> userList, boolean updateSupport, String operName);

}
