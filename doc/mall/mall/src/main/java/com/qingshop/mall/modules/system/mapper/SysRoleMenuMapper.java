package com.qingshop.mall.modules.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingshop.mall.modules.system.entity.SysRoleMenu;

/**
 *
 * SysRoleMenu 表数据库控制层接口
 *
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

	/**
	 * 根据用户Id获取用户所拥有的菜单
	 */
	public List<Long> selectRoleMenuIdsByUserId(Long uid);

}