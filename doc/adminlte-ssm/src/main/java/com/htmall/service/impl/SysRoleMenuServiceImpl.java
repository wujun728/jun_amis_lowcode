package com.htmall.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htmall.entity.SysRoleMenu;
import com.htmall.mapper.SysMenuMapper;
import com.htmall.mapper.SysRoleMenuMapper;
import com.htmall.service.ISysRoleMenuService;

/**
 *
 * SysRoleMenu 表数据服务层接口实现类
 *
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public void addAuth(String roleId, String[] menuIds) {
		// TODO Auto-generated method stub

		/**
		 * 删除原有权限
		 */
		this.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
		/**
		 * 重新授权
		 */
		if (ArrayUtils.isNotEmpty(menuIds)) {
			for (String menuId : menuIds) {
				SysRoleMenu sysRoleMenu2 = new SysRoleMenu();
				sysRoleMenu2.setRoleId(roleId);
				sysRoleMenu2.setMenuId(menuId);
				this.save(sysRoleMenu2);
			}
		}
	}

	@Override
	public List<SysRoleMenu> selectByRole(String roleId) {
		QueryWrapper<SysRoleMenu> ew = new QueryWrapper<SysRoleMenu>();
		ew.eq("role_id", roleId);
		return this.list(ew);

	}

	@Override
	public Set<String> findMenusByUid(String id) {
		List<String> list = sysMenuMapper.selectResourceByUid(id);
		return new HashSet<>(list);
	}

}