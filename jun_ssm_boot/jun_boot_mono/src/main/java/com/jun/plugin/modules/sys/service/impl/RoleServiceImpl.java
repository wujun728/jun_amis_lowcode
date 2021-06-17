package com.jun.plugin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.sys.mapper.MenuMapper;
import com.jun.plugin.modules.sys.mapper.RoleMapper;
import com.jun.plugin.modules.sys.mapper.RoleMenuMapper;
import com.jun.plugin.modules.sys.mapper.UserMapper;
import com.jun.plugin.modules.sys.mapper.UserRoleMapper;
import com.jun.plugin.modules.sys.model.SysMenu;
import com.jun.plugin.modules.sys.model.SysRole;
import com.jun.plugin.modules.sys.model.SysRoleMenu;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.model.SysUserRole;
import com.jun.plugin.modules.sys.service.RoleService;

import tk.mybatis.mapper.entity.Example;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public List<SysRole> listRoles(SysRole role) {
		Example example = new Example(SysRole.class);
		Example.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(role.getName())) {
			criteria.andLike("name", "%" + role.getName() + "%");
		}
		criteria.andEqualTo("status", CoreConst.STATUS_VALID);
		return roleMapper.selectByExample(example);
	}

	@Override
	public int save(SysRole role) {
		role.setRoleId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
		role.setStatus(1);
		role.setCreateTime(new Date());
		return roleMapper.insert(role);
	}

	@Override
	public int updateStatusBatch(Long[] roleIds, Integer status) {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("roleIds", roleIds);
		params.put("status", status);
		return roleMapper.updateStatusBatch(params);
	}

	@Override
	public SysRole getSysRoleById(Long id) {
		SysRole role = new SysRole();
		role.setRoleId(id);
		return roleMapper.selectByPrimaryKey(role);
	}

	@Override
	public int updateByRoleId(SysRole role) {
		Map<String, Object> params = new HashMap<>(3);
		params.put("name", role.getName());
		params.put("description", role.getDescription());
		params.put("role_id", role.getRoleId());
		return roleMapper.updateByRoleId(params);
	}

	@Override
	public ResponseVo<?> addAssignMenu(Long roleId, List<Long> menuIds) {
		try {
			if (roleId == null) {
				return ResultUtil.error("分配权限失败");
			}
			Example example = new Example(SysRoleMenu.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("roleId", roleId);
			roleMenuMapper.deleteByExample(example);
			// 清空权限分配
			if (StringUtils.isEmpty(menuIds)) {
				return ResultUtil.success("分配权限成功");
			}
			List<SysRoleMenu> sysRoleMenuList = new ArrayList<SysRoleMenu>();
			for (Long menuId : menuIds) {
				SysRoleMenu roleMenu = new SysRoleMenu();
				roleMenu.setRolePermId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(menuId);
				sysRoleMenuList.add(roleMenu);
			}
			int insertResult = roleMenuMapper.insertList(sysRoleMenuList);
			if (insertResult == 0) {
				return ResultUtil.error("分配权限失败");
			}
			return ResultUtil.success("分配权限成功");
		} catch (Exception e) {
			return ResultUtil.error("分配权限失败");
		}
	}

	@Override
	public List<SysUser> listAllUsersByRoleIds(Long[] roleIds) {
		return userMapper.listAllUsersByRoleIds(roleIds);
	}

	@Override
	public List<SysMenu> listMenusByRoleId(Long roleId) {
		return menuMapper.listMenusByRoleId(roleId);
	}

	@Override
	public Set<String> listRoleIdsByUserId(Long userId) {
		return userRoleMapper.listRoleIdsByUserId(userId);
	}

	@Override
	public Set<String> listUserIdsByRoleId(Long roleId) {
		return userRoleMapper.listUserIdsByRoleId(roleId);
	}

	@Override
	public ResponseVo<?> cancelAssign(Long roleId, Long userId) {
		if (roleId == null || userId == null) {
			return ResultUtil.error("取消分配失败");
		}
		Example example = new Example(SysUserRole.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("roleId", roleId);
		criteria.andEqualTo("userId", userId);
		int deleteResult = userRoleMapper.deleteByExample(example);
		if (deleteResult == 0) {
			return ResultUtil.error("取消分配失败");
		}
		return ResultUtil.success("取消分配失败");
	}

	@Override
	public ResponseVo<?> assignUser(Long roleId, Long userId) {
		if (roleId == null || userId == null) {
			return ResultUtil.error("取消分配失败");
		}
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserRoleId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
		sysUserRole.setRoleId(roleId);
		sysUserRole.setUserId(userId);
		int insertResult = userRoleMapper.insert(sysUserRole);
		if (insertResult == 0) {
			return ResultUtil.error("取消分配失败");
		}
		return ResultUtil.success("分配用户成功");
	}

	@Override
	public Set<String> listRoleNameByUserId(Long userId) {
		return roleMapper.listRoleNameByUserId(userId);
	}
}
