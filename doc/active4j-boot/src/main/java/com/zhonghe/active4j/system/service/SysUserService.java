package com.zhonghe.active4j.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.system.entity.SysRoleEntity;
import com.zhonghe.active4j.system.entity.SysUserEntity;
import com.zhonghe.active4j.system.model.ActiveUser;

public interface SysUserService extends IService<SysUserEntity> {

	
	public void saveUser(SysUserEntity user, String roleIds);
	
	
	/**
	 * 编辑用户， 角色修改
	 * @param user
	 * @param roleIds
	 */
	public void saveOrUpdateUser(SysUserEntity user, String roleIds);
	
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(SysUserEntity user);
	
	
	/**
	 * 获取用户所属的角色
	 * @param user
	 * @return
	 */
	public List<SysRoleEntity> getUserRoles(SysUserEntity user);
	
	
	/**
	 * 根据用户名取得用户
	 * @param userName
	 * @return
	 */
	public SysUserEntity getUserByUseName(String userName);
	
	/**
	 * 根据用户信息  组装shiro session用户
	 * @param user
	 * @return
	 */
	public ActiveUser getActiveUserByUser(SysUserEntity user);
}
