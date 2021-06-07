package com.active4j.hr.system.service;

import java.util.List;

import com.active4j.hr.system.entity.SysFunctionEntity;
import com.active4j.hr.system.entity.SysRoleEntity;
import com.active4j.hr.system.entity.SysUserEntity;
import com.active4j.hr.system.model.ActiveUser;
import com.active4j.hr.system.model.SysUserModel;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysUserService extends IService<SysUserEntity> {

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
	
	/**
	 * 
	 * @description
	 *  	根据用户ID 获取用户所有菜单
	 * @params
	 *      userId 用户ID
	 * @return List<SysMenuEntity>
	 * @author guyp
	 * @time 2020年1月3日 下午1:18:08
	 */
	public List<SysFunctionEntity> findMenuByUserId(String userId);
	
	
	/**
	 * 
	 * @description
	 *  	用户的保存
	 * @return void
	 * @author 麻木神
	 * @time 2020年1月28日 下午10:19:49
	 */
	public void saveUser(SysUserEntity user, String[] roleIds);
	
	/**
	 * 
	 * @description
	 *  	根据用户ID 获取用户所属角色集合
	 * @params
	 *      userId  用户ID
	 * @return List<SysRoleEntity>
	 * @author 麻木神
	 * @time 2020年1月28日 下午11:40:01
	 */
	public List<SysRoleEntity> getUserRoleByUserId(String userId);
	
	/**
	 * 
	 * @description
	 *  	编辑用户
	 * @return void
	 * @author 麻木神
	 * @time 2020年1月29日 上午12:26:43
	 */
	public void saveOrUpdateUser(SysUserEntity user, String[] roleIds);

	/**
	 * 
	 * @description
	 *  	根据id查询用户个人资料
	 * @params
	 * @return SysUserModel
	 * @author guyp
	 * @time 2020年2月8日 下午12:36:47
	 */
	public SysUserModel getInfoByUserId(String userId);
	
	/**
	 * 
	 * @description
	 *  	删除关联的用户信息，然后删除用户
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2020年2月8日 下午4:25:56
	 */
	public void delete(String userId);
}
