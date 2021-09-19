package com.jun.plugin.api.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jun.plugin.api.entity.SysRoleMenu;

/**
 *
 * SysRoleMenu 表数据库控制层接口
 *
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

	/**
	 * 根据用户Id获取用户所在角色的权限
	 */
	public List<String> selectRoleMenuIdsByUserId(String uid);
	
}