package com.element.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.element.modules.sys.entity.SysUserEntity;
import com.element.common.utils.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 */
public interface SysUserService extends IService<SysUserEntity> {

	PageUtils listUserByPage(Map<String, Object> params);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> listMenuIdByCreateUserId(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity getByUserName(String username);

	/**
	 * 保存用户
	 */
	void saveUser(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] userIds);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	boolean updatePassword(Long userId, String password, String newPassword);
}
