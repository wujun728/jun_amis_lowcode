package com.qingshop.mall.modules.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.system.entity.SysRoleMenu;

/**
 *
 * SysRoleMenu 表数据服务层接口
 *
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

	/**
	 * 角色授权
	 */
	void addAuth(Long roleId, Long[] menuIds);

	/**
	 * 根据用户Id获取用户所拥有的菜单
	 */
	List<Long> selectRoleMenuIdsByUserId(Long uid);

}