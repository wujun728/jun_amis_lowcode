package com.htmall.service;

import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.htmall.entity.SysUserRole;

/**
 *
 * SysUserRole 表数据服务层接口
 *
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

	/**
	 * 获取用户的角色
	 * 
	 * @param uid
	 * @return
	 */
	Set<String> findRolesByUid(String uid);
}