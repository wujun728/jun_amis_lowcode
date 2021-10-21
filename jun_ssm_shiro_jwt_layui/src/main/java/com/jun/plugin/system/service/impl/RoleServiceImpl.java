package com.jun.plugin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.system.common.Constant;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Menu;
import com.jun.plugin.system.domain.Role;
import com.jun.plugin.system.mapper.MenuMapper;
import com.jun.plugin.system.mapper.RoleMapper;
import com.jun.plugin.system.service.MenuService;
import com.jun.plugin.system.service.RoleService;
import com.jun.plugin.system.vo.RoleVo;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/19
 *
 * 
 * 
 * @since JDK 1.8
 **/

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 查询角色数据
	 *
	 * @param roleVo
	 * @return
	 */
	@Override
	public DataGridView queryAllRole(RoleVo roleVo) {
		IPage<Role> page = new Page<>(roleVo.getPage(), roleVo.getLimit());
		QueryWrapper<Role> qw = new QueryWrapper<>();
		qw.eq(roleVo.getAvailable() != null, "available", roleVo.getAvailable());
		qw.like(StringUtils.isNotBlank(roleVo.getName()), "name", roleVo.getName());
		qw.like(StringUtils.isNotBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
		this.roleMapper.selectPage(page, qw);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 保存角色数据
	 *
	 * @param role
	 */
	@Override
	public Role saveRole(Role role) {
		this.roleMapper.insert(role);
		return role;
	}

	/**
	 * 更新角色信息
	 *
	 * @param role
	 */
	@Override
	public Role updateRole(Role role) {
		this.roleMapper.updateById(role);
		return role;
	}

	/**
	 * 根据角色ID查询当前角色已拥有的菜单和权限 集合
	 *
	 * @param id
	 * @return 菜单和权限id
	 */
	@Override
	public List<Integer> queryMenuIdsByRid(Integer id) {
		return this.roleMapper.queryMenuIdsByRid(id);
	}

	/**
	 * 保存角色和菜单权限 关系
	 *
	 * @param rid
	 * @param mids
	 */
	@Override
	public void saveRoleMenu(Integer rid, Integer[] mids) {
		// 根据角色ID删除sys_role_menu 里面的数据
		this.roleMapper.deleteRoleMenuByRid(rid);
		// 判断是否为空啥的
		if (null != mids && mids.length > 0) {
			// 进行批量 优化删除
			this.roleMapper.insertRoleMenu(rid, mids);
		}
	}

	/**
	 * 查询可用的角色
	 *
	 * @param roleVo
	 * @return
	 */
	@Override
	public DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo) {
		QueryWrapper<Role> qw = new QueryWrapper<>();
		qw.eq(roleVo.getAvailable() != null, "available", roleVo.getAvailable());
		List<Role> roles = this.roleMapper.selectList(qw);
		// 根据用户ID查询已拥有的角色ID
		// select rid from sys_role_user where uid = uid
		List<Integer> roleIds = this.roleMapper.queryRoleIdsByUserId(roleVo.getUserId());
		// 因为前端表格需要 lay_checked 所以我们进行组装
		List<Map<String, Object>> lists = new ArrayList<>();
		// 查询到所以有角色
		for (Role role : roles) {
			// 定义是否选中
			Boolean LAY_CHECKED = false;
			// 循环该用户的角色
			for (Integer roleId : roleIds) {
				// 判断 有这个角色 进行选中
				if (role.getId().equals(roleId)) {
					LAY_CHECKED = true;
					break;
				}
			}
			// 组装数据
			Map<String, Object> map = new HashMap<>();
			map.put("id", role.getId());
			map.put("name", role.getName());
			map.put("remark", role.getRemark());
			map.put("LAY_CHECKED", LAY_CHECKED);
			lists.add(map);
		}
		// 返回
		return new DataGridView(Long.valueOf(lists.size()), lists);
	}

	/**
	 * 根据用户ID 查询拥有的角色名称
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<String> queryRoleNamesByUid(Integer id) {
		// 根据用户ID 查询角色名称
		List<Integer> roles = this.roleMapper.queryRoleIdsByUserId(id);
		// 根据角色id 查询对应的角色 名称
		if (null != roles && roles.size() > 0) {
			QueryWrapper<Role> qw = new QueryWrapper<>();
			qw.eq("available", Constant.AVAILABLE_TRUE);
			qw.in("id", roles);
			List<Role> roles1 = this.roleMapper.selectList(qw);
			ArrayList<String> rolesName = new ArrayList<>();
			for (Role role : roles1) {
				rolesName.add(role.getName());
			}
			return rolesName;
		}
		return null;
	}

	/**
	 * 重写mq删除方法
	 *
	 * @param id
	 * @return
	 */
	@Override
	public boolean removeById(Serializable id) {
		// 根据角色ID删除角色和菜单之间的关系
		this.roleMapper.deleteRoleMenuByRid(id);
		// 根据角色ID删除角色和用户之间的关系
		this.roleMapper.deleteRoleUserByRid(id);
		// 删除角色表数据
		return super.removeById(id);
	}

}
