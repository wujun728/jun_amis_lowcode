package com.jun.plugin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import com.jun.plugin.system.vo.MenuVo;

/**
 * ClassName: MenuServiceImpl Description: layui date: 2020/4/14 18:39
 *
 * 
 * 
 * @since JDK 1.8
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 只查询菜单
	 *
	 * @return
	 */
	@Override
	public List<Menu> queryAllMenuForList() {
		// 定义wrapper
		QueryWrapper<Menu> qw = new QueryWrapper<>();
		qw.eq("available", Constant.AVAILABLE_TRUE);
		qw.and(new Consumer<QueryWrapper<Menu>>() {
			@Override
			public void accept(QueryWrapper<Menu> menuQueryWrapper) {
				// 条件 并且 查询type要等于 type_top的
				// 和(或者查询type要等于 type_left的 条件
				menuQueryWrapper.eq("type", Constant.MENU_TYPE_TOP).or().eq("type", Constant.MENU_TYPE_LEFT);
			}
		});
		qw.orderByAsc("ordernum");
		return this.menuMapper.selectList(qw);
	}

	/**
	 * 查询菜单和权限
	 *
	 * @param menuVo
	 * @return
	 */
	@Override
	public DataGridView queryAllMenu(MenuVo menuVo) {
		QueryWrapper<Menu> qw = new QueryWrapper<>();
		qw.eq(menuVo.getAvailable() != null, "available", menuVo.getAvailable());
		qw.orderByAsc("ordernum");
		List<Menu> menus = this.menuMapper.selectList(qw);
		return new DataGridView(Long.valueOf(menus.size()), menus);
	}

	/**
	 * 根据用户ID查询菜单 实现思路： 根据用户id查询 用户关系表拥有的角色 的id 在根据 角色id 查询 对应的菜单id
	 *
	 * @param uid 用户id
	 * @return
	 */
	@Override
	public List<Menu> queryMenuForListByUserId(Integer uid) {
		/*
		 * 考虑到分库分表操作 不能使用连接查询 所以使用单表查 记住 单表查是不会有 任何问题的
		 */
		// 根据用户ID 会查询角色ID 的集合
		List<Integer> roleIds = this.roleMapper.queryRoleIdsByUserId(uid);
		// 判断
		if (roleIds.size() > 0 && null != roleIds) {
			// 根据 角色id 的集合查询 菜单的id集合 中间表 sys_role_menu 查询角色对应的菜单
			List<Integer> menuIds = this.roleMapper.queryMenuIdsByRoleIds(roleIds);
			// 进行筛选菜单了 把权限筛选出去
			if (null != menuIds && menuIds.size() > 0) {
				QueryWrapper<Menu> qw = new QueryWrapper<>();
				qw.eq("available", Constant.AVAILABLE_TRUE);
				// 或者使用
				qw.and(new Consumer<QueryWrapper<Menu>>() {
					@Override
					public void accept(QueryWrapper<Menu> menuQueryWrapper) {
						// 条件 并且 查询type要等于 type_top的
						// 和(或者查询type要等于 type_left的 条件
						menuQueryWrapper.eq("type", Constant.MENU_TYPE_TOP).or().eq("type", Constant.MENU_TYPE_LEFT);
					}
				});
				// 条件逻辑 查询出来的菜单id
				qw.in("id", menuIds);
				qw.orderByAsc("ordernum");
				List<Menu> menus = this.menuMapper.selectList(qw);
				return menus;
			} else {
				// 没有菜单也直接返回
				return new ArrayList<>();
			}
		} else {
			// 用户拥有的角色 没有菜单 则直接返回空集合
			return new ArrayList<>();
		}
	}

	/**
	 * 查询菜单最大 排序码
	 *
	 * @return
	 */
	@Override
	public Integer queryMenuMaxOrderNum() {
		return this.menuMapper.queryMenuMaxOrderNum();
	}

	/**
	 * 添加菜单
	 *
	 * @param menu
	 * @return
	 */
	@CachePut(cacheNames = "com.jun.plugin.system.service.impl.MenuServiceImpl", key = "#result.id")
	@Override
	public Menu saveMenu(Menu menu) {
		this.menuMapper.insert(menu);
		return menu;
	}

	/**
	 * 更新菜单
	 *
	 * @param menu
	 * @return
	 */
	@CachePut(cacheNames = "com.jun.plugin.system.service.impl.MenuServiceImpl", key = "#result.id")
	@Override
	public Menu updateMenu(Menu menu) {
		this.menuMapper.updateById(menu);
		return menu;
	}

	/**
	 * 删除菜单 也要删除 缓存菜单
	 *
	 * @param id
	 * @return
	 */
	@CacheEvict(cacheNames = "com.jun.plugin.system.service.impl.MenuServiceImpl", key = "#id")
	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	/**
	 * 根据菜单ID 查询子部门个数
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Integer queryMenuChildrenCountById(Integer id) {
		return this.menuMapper.queryMenuChildrenCountById(id);
	}

	/**
	 * 根据用户ID 查询拥有的权限 联查
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<String> queryPermissionCodesByUserId(Integer id) {

		// 根据用户ID 查询角色
		List<Integer> rolesId = this.roleMapper.queryRoleIdsByUserId(id);
		if (null != rolesId && rolesId.size() > 0) {
			// 根据角色ID 查询权限菜单
			List<Integer> menuids = this.roleMapper.queryMenuIdsByRoleIds(rolesId);
			if (null != menuids && menuids.size() > 0) {
				// 根据权限菜单id 查询 查询权限菜单
				QueryWrapper<Menu> qw = new QueryWrapper<>();
				qw.eq("available", Constant.AVAILABLE_TRUE);
				qw.eq("type", Constant.MENU_TYPE_PERMISSION);
				qw.in("id", menuids);
				qw.orderByAsc("ordernum");
				List<Menu> menus = this.menuMapper.selectList(qw);
				List<String> permissions = new ArrayList<>();
				for (Menu menu : menus) {
					permissions.add(menu.getTypecode());
				}
				return permissions;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
