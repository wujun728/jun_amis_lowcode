package com.jun.plugin.modules.sys.mapper;

import com.jun.plugin.common.mapper.MyMapper;
import com.jun.plugin.modules.sys.model.SysRoleMenu;

public interface RoleMenuMapper extends MyMapper<SysRoleMenu> {

	int countRoleMenuByMenuId(Long menuId);
}