package com.element.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色与菜单对应关系
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> listMenuIdByRoleId(Long roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
