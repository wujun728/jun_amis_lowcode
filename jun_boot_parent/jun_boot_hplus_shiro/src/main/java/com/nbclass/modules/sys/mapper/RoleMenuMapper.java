package com.nbclass.modules.sys.mapper;

import com.nbclass.common.mapper.MyMapper;
import com.nbclass.modules.sys.model.SysRoleMenu;

public interface RoleMenuMapper extends MyMapper<SysRoleMenu> {

	int countRoleMenuByMenuId(Long menuId);
}