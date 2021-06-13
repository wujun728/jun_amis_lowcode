package com.qingshop.mall.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.modules.system.entity.SysRole;
import com.qingshop.mall.modules.system.entity.SysRoleMenu;
import com.qingshop.mall.modules.system.mapper.SysRoleMapper;
import com.qingshop.mall.modules.system.service.ISysRoleMenuService;
import com.qingshop.mall.modules.system.service.ISysRoleService;

/**
 * SysRole 表数据服务层接口实现类
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
	
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;

	@Override
	@Transactional
	public void delete(Long id) {
		this.removeById(id);
		//删除所关联的菜单关系
		sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
	}
	
}