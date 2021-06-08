package com.htmall.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htmall.common.anno.Log;
import com.htmall.common.bean.Rest;
import com.htmall.common.controller.BaseController;
import com.htmall.entity.SysRole;
import com.htmall.entity.SysRoleMenu;
import com.htmall.entity.SysUser;
import com.htmall.entity.SysUserRole;
import com.htmall.entity.vo.TreeMenuAllowAccess;
import com.htmall.service.ISysMenuService;
import com.htmall.service.ISysRoleMenuService;
import com.htmall.service.ISysRoleService;
import com.htmall.service.ISysUserRoleService;
import com.htmall.service.ISysUserService;

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
	 * 分页查询角色
	 */
	@RequiresPermissions("listRole")
	@RequestMapping("/list/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "15") Integer pageSize, String search, Model model) {

		Page<SysRole> page = getPage(pageNumber, pageSize);

		// 查询分页
		QueryWrapper<SysRole> ew = new QueryWrapper<SysRole>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("role_name", search);
			model.addAttribute("search", search);
		}
		ew.orderByAsc("create_time");
		IPage<SysRole> pageData = sysRoleService.page(page, ew);
		model.addAttribute("pageSize", pageSize);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("records", pageData.getRecords());
		resultMap.put("currentPage", pageData.getCurrent());
		resultMap.put("sizePage", pageData.getSize());
		resultMap.put("totalPage", pageData.getPages());
		resultMap.put("total", pageData.getTotal());
		model.addAttribute("pageData", resultMap);
		return "system/role/list";
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
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(SysRole role) {
		role.setCreateTime(new Date());
		sysRoleService.save(role);
		return Rest.ok();

	}

	/**
	 * 删除角色
	 */
	@RequiresPermissions("deleteRole")
	@Log("删除角色")
	@RequestMapping("/delete")
	@ResponseBody
	public Rest delete(String id) {
		sysRoleService.removeById(id);
		return Rest.ok();
	}

	/**
	 * 批量删除角色
	 */
	@RequiresPermissions("deleteBatchRole")
	@Log("批量删除角色")
	@RequestMapping("/deleteBatch")
	@ResponseBody
	public Rest deleteBatch(@RequestParam("id[]") List<String> ids) {
		sysRoleService.removeByIds(ids);
		return Rest.ok();
	}

	/**
	 * 编辑角色
	 */
	@RequiresPermissions("editRole")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		SysRole sysRole = sysRoleService.getById(id);
		model.addAttribute(sysRole);
		return "system/role/edit";
	}

	/**
	 * 执行编辑角色
	 */
	@RequiresPermissions("editRole")
	@Log("编辑角色")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(SysRole sysRole, Model model) {
		sysRoleService.updateById(sysRole);
		return Rest.ok();
	}

	/**
	 * 权限
	 */
	@RequiresPermissions("authRole")
	@RequestMapping("/auth/{id}")
	public String auth(@PathVariable String id, Model model) {
		SysRole sysRole = sysRoleService.getById(id);

		if (sysRole == null) {
			throw new RuntimeException("该角色不存在");
		}

		List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", id));

		List<String> menuIds = sysRoleMenus.stream().map(m -> m.getMenuId()).collect(Collectors.toList());

		List<TreeMenuAllowAccess> treeMenuAllowAccesses = sysMenuService.selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds, "0");

		model.addAttribute("sysRole", sysRole);
		model.addAttribute("treeMenuAllowAccesses", treeMenuAllowAccesses);

		return "system/role/auth";
	}

	/**
	 * 权限
	 */
	@RequiresPermissions("authRole")
	@Log("角色分配权限")
	@RequestMapping("/doAuth")
	@ResponseBody
	public Rest doAuth(String roleId, @RequestParam(value = "mid[]", required = false) String[] mid) {
		sysRoleMenuService.addAuth(roleId, mid);
		return Rest.ok("OK,授权成功,1分钟后生效  ~");
	}

	/**
	 * 获取角色下的所有用户
	 */
	@RequestMapping("/getUsers")
	public String getUsers(String roleId, Model model) {

		List<SysUserRole> sysUserRoles = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("role_id", roleId));

		List<String> userIds = sysUserRoles.stream().map(m -> m.getUserId()).collect(Collectors.toList());

		List<SysUser> users = new ArrayList<SysUser>();

		if (userIds.size() > 0) {
			QueryWrapper<SysUser> ew = new QueryWrapper<SysUser>();
			ew.in("id", userIds);
			users = sysUserService.list(ew);
		}

		model.addAttribute("users", users);
		return "system/role/users";
	}

	/**
	 * 获取指定角色的用户数量
	 */
	@RequestMapping("/getCount")
	@ResponseBody
	public String getCount(String roleId) {

		int count = sysUserRoleService.count(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
		return String.valueOf(count);
	}

}
