package com.qingshop.mall.modules.system.service;

import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.system.entity.SysUserRole;

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
	Set<String> findRolesByUid(Long uid);
}