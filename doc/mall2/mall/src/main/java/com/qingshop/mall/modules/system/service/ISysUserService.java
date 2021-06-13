package com.qingshop.mall.modules.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.system.entity.SysUser;

/**
 *
 * SysUser 表数据服务层接口
 *
 */
public interface ISysUserService extends IService<SysUser> {

	/**
	 * 分页查询用户
	 */
	Page<Map<Object, Object>> selectUserPage(Page<Map<Object, Object>> page, String search);

	/**
	 * 保存用户
	 */
	void insertUser(SysUser user, Long[] roleId);

	/**
	 * 更新用户
	 */
	void updateUser(SysUser sysUser, Long[] roleId);

	/**
	 * 删除用户
	 */
	void delete(Long id);

	/**
	 * 查询用户权限
	 */
	List<String> queryAllPermsByUserId(Long userId);

}