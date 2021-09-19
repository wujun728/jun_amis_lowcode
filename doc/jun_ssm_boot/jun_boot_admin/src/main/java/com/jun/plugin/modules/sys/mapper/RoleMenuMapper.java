package com.jun.plugin.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.modules.sys.model.SysRoleMenu;

public interface RoleMenuMapper extends BaseMapper<SysRoleMenu> {

	int countRoleMenuByMenuId(Long menuId);
}