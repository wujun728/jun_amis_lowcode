package com.nbclass.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbclass.modules.sys.model.SysRoleMenu;

public interface RoleMenuMapper extends BaseMapper<SysRoleMenu> {

	int countRoleMenuByMenuId(Long menuId);
}