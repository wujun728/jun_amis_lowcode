package com.qingshop.mall.modules.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.annotation.Log;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.system.entity.SysRole;
import com.qingshop.mall.modules.system.entity.SysRoleMenu;
import com.qingshop.mall.modules.system.entity.SysUser;
import com.qingshop.mall.modules.system.entity.SysUserRole;
import com.qingshop.mall.modules.system.service.ISysMenuService;
import com.qingshop.mall.modules.system.service.ISysRoleMenuService;
import com.qingshop.mall.modules.system.service.ISysRoleService;
import com.qingshop.mall.modules.system.service.ISysUserRoleService;
import com.qingshop.mall.modules.system.service.ISysUserService;
import com.qingshop.mall.modules.system.vo.TreeMenuAllowAccess;

/**
 * 角色控制器
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

	/**
	 * 角色服务
	 */
	@Autowired
	private ISysRoleService sysRoleService;
	/**
	 * 角色用户服务
	 */
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	/**
	 * 用户服务
	 */
	@Autowired
	private ISysUserService sysUserService;
	/**
	 * 菜单服务
	 */
	@Autowired
	private ISysMenuService sysMenuService;
	/**
	 * 角色权限服务
	 */
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;

	/**
	 * 列表页
	 */
	@RequiresPermissions("listRole")
	@RequestMapping("/list")
	public String list() {
		return "system/role/list";
	}

	/**
	 * 分页查询角色
	 */
	@RequiresPermissions("listRole")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		Page<SysRole> page = getPage(pageIndex, length);
		// 查询分页
		QueryWrapper<SysRole> ew = new QueryWrapper<SysRole>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("roleName", search);
		}
		ew.orderByDesc("create_time");
		IPage<SysRole> pageData = sysRoleService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 新增角色
	 */
	@RequiresPermissions("addRole")
	@RequestMapping("/add")
	public String add(Model model) {
		return "system/role/add";
	}

	/**
	 * 执行新增角色
	 */
	@RequiresPermissions("addRole")
	@Log("创建角色")
	@PostMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(SysRole role) {
		role.setRoleId(DistributedIdWorker.nextId());
		role.setCreateTime(new Date());
		sysRoleService.save(role);
		return Rest.ok();

	}

	/**
	 * 删除角色
	 */
	@RequiresPermissions("deleteRole")
	@Log("删除角色")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		int count = sysUserRoleService.count(new QueryWrapper<SysUserRole>().eq("role_id", id));
		if (count > 0) {
			return Rest.failure("该角色已分配用户无法删除！");
		}
		sysRoleService.delete(id);
		return Rest.ok();
	}

	/**
	 * 编辑角色
	 */
	@RequiresPermissions("editRole")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		SysRole sysRole = sysRoleService.getById(id);
		model.addAttribute(sysRole);
		return "system/role/edit";
	}

	/**
	 * 执行编辑角色
	 */
	@RequiresPermissions("editRole")
	@Log("编辑角色")
	@PostMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(SysRole sysRole) {
		sysRoleService.updateById(sysRole);
		return Rest.ok();
	}

	/**
	 * 权限
	 */
	@RequiresPermissions("authRole")
	@RequestMapping("/auth/{id}")
	public String auth(@PathVariable Long id, Model model) {

		SysRole sysRole = sysRoleService.getById(id);
		if (sysRole == null) {
			throw new RuntimeException("该角色不存在");
		}
		List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
		List<Long> menuIds = Lists.transform(sysRoleMenus, input -> input.getMenuId());
		List<TreeMenuAllowAccess> treeMenuAllowAccesses = sysMenuService.selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds, 0L);
		model.addAttribute("sysRole", sysRole);
		model.addAttribute("treeMenuAllowAccesses", treeMenuAllowAccesses);
		return "system/role/auth";
	}

	/**
	 * 权限
	 */
	@RequiresPermissions("authRole")
	@Log("角色分配权限")
	@PostMapping("/doAuth")
	@ResponseBody
	public Rest doAuth(Long roleId, @RequestParam(value = "mid[]", required = false) Long[] mid) {
		sysRoleMenuService.addAuth(roleId, mid);
		return Rest.ok("授权成功,刷新页面后生效");
	}

	/**
	 * 获取角色下的所有用户
	 */
	@RequestMapping("/assignUsers/{roleId}")
	public String assignUsers(@PathVariable Long roleId, Model model) {
		model.addAttribute("roleId", roleId);
		return "system/role/assign";
	}

	/**
	 * 获取角色下的所有用户
	 */
	@RequestMapping("/assignUsers/listPage")
	@ResponseBody
	public Rest assignUsersListPage(String roleId, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		List<SysUserRole> sysUserRoles = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
		List<Long> userIds = Lists.transform(sysUserRoles, input -> input.getUserId());
		if (userIds.size() > 0) {
			Page<SysUser> page = getPage(pageIndex, length);
			QueryWrapper<SysUser> ew = new QueryWrapper<SysUser>();
			ew.in("user_id", userIds);
			ew.orderByDesc("create_time");
			IPage<SysUser> pageData = sysUserService.page(page, ew);
			resultMap.put("iTotalDisplayRecords", pageData.getTotal());
			resultMap.put("iTotalRecords", pageData.getTotal());
			resultMap.put("aaData", pageData.getRecords());
		} else {
			resultMap.put("iTotalDisplayRecords", 0);
			resultMap.put("iTotalRecords", 0);
			resultMap.put("aaData", new ArrayList<>());
		}
		return resultMap;
	}

	/**
	 * 获取角色下的所有用户
	 */
	@RequestMapping("/getUsers/{roleId}")
	public String getUsers(@PathVariable Long roleId, Model model) {
		model.addAttribute("roleId", roleId);
		return "system/role/users";
	}

	/**
	 * 获取角色下的所有用户
	 */
	@RequestMapping("/getUsers/listPage")
	@ResponseBody
	public Rest getUsersListPage(String search, String roleId, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		List<SysUserRole> sysUserRoles = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
		List<Long> userIds = Lists.transform(sysUserRoles, input -> input.getUserId());
		Page<SysUser> page = getPage(pageIndex, length);
		QueryWrapper<SysUser> ew = new QueryWrapper<SysUser>();
		if (StringUtils.isNotEmpty(userIds)) {
			ew.notIn("user_id", userIds);
		}
		if (StringUtils.isNotBlank(search)) {
			ew.like("user_name", search);
		}
		ew.orderByDesc("create_time");
		IPage<SysUser> pageData = sysUserService.page(page, ew);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 执行分配角色
	 */
	@RequiresPermissions("editRole")
	@Log("执行分配角色")
	@PostMapping("/addAssignUser")
	@ResponseBody
	public Rest addAssignUser(String userIds, Long roleId) {
		String[] idArry = userIds.split(",");
		if (ArrayUtils.isNotEmpty(idArry)) {
			List<SysUserRole> list = new ArrayList<>();
			for (String uid : idArry) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setUserRoleId(DistributedIdWorker.nextId());
				sysUserRole.setUserId(Long.valueOf(uid));
				sysUserRole.setRoleId(roleId);
				list.add(sysUserRole);
			}
			sysUserRoleService.saveBatch(list);
		}
		return Rest.ok();
	}

	/**
	 * 删除角色
	 */
	@RequiresPermissions("deleteRole")
	@Log("删除用户授权")
	@PostMapping("/deleteAssignUser")
	@ResponseBody
	public Rest deleteAssignUser(Long userId, Long roleId) {
		if (userId != null && roleId != null) {
			sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", userId).eq("role_id", roleId));
		} else {
			return Rest.failure();
		}
		return Rest.ok();
	}

}
