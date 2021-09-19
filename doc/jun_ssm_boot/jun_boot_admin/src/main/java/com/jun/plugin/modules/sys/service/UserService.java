package com.jun.plugin.modules.sys.service;

import java.io.Serializable;
import java.util.List;

import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.vo.UserOnlineVo;

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

    /**
     * 查询在线用户
     * @param userOnlineVo
     * @return list
     */
    List<UserOnlineVo> selectOnlineUsers(UserOnlineVo userOnlineVo);

    /**
     * 踢出用户
     * @param sessionId 会话id
     * @param username 用户名
     */
    void kickout(Serializable sessionId, String username);
    

}
