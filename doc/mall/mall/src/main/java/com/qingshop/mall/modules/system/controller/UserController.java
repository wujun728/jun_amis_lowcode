package com.qingshop.mall.modules.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.framework.annotation.Log;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.system.entity.SysRole;
import com.qingshop.mall.modules.system.entity.SysUser;
import com.qingshop.mall.modules.system.entity.SysUserRole;
import com.qingshop.mall.modules.system.service.ISysDeptService;
import com.qingshop.mall.modules.system.service.ISysRoleService;
import com.qingshop.mall.modules.system.service.ISysUserRoleService;
import com.qingshop.mall.modules.system.service.ISysUserService;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysRoleService sysRoleService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private ISysDeptService sysDeptService;

	/**
	 * 分页查询用户
	 */
	@RequiresPermissions("listUser")
	@RequestMapping("/list")
	public String list() {
		return "system/user/list";
	}

	/**
	 * 分页查询用户
	 */
	@RequiresPermissions("listUser")
	@RequestMapping("/listPage")
	@ResponseBody
	public Rest listPage(String search, Integer start, Integer length) {
		Integer pageIndex = start / length + 1;
		Rest resultMap = new Rest();
		// 查询分页
		QueryWrapper<SysUser> ew = new QueryWrapper<SysUser>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("roleName", search);
		}
		Page<Map<Object, Object>> page = getPage(pageIndex, length);
		Page<Map<Object, Object>> pageData = sysUserService.selectUserPage(page, search);
		resultMap.put("iTotalDisplayRecords", pageData.getTotal());
		resultMap.put("iTotalRecords", pageData.getTotal());
		resultMap.put("aaData", pageData.getRecords());
		return resultMap;
	}

	/**
	 * 新增用户
	 */
	@RequiresPermissions("addUser")
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("roleList", sysRoleService.list(null));
		model.addAttribute("deptList", sysDeptService.list(null));
		return "system/user/add";
	}

	/**
	 * 执行新增
	 */
	@Log("创建用户")
	@RequiresPermissions("addUser")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(SysUser user, @RequestParam(value = "roleId[]", required = false) Long[] roleId) {

		user.setUserId(DistributedIdWorker.nextId());
		sysUserService.insertUser(user, roleId);
		return Rest.ok();
	}

	/**
	 * 删除用户
	 */
	@Log("删除用户")
	@RequiresPermissions("deleteUser")
	@PostMapping("/delete")
	@ResponseBody
	public Rest delete(Long id) {
		if (id == 1L) {
			return Rest.failure("超级管理员角色不能删除！");
		}
		sysUserService.delete(id);
		return Rest.ok();
	}

	/**
	 * 编辑用户
	 */
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("editUser")
	public String edit(@PathVariable Long id, Model model) {
		SysUser sysUser = sysUserService.getById(id);

		List<SysRole> sysRoles = sysRoleService.list(null);
		QueryWrapper<SysUserRole> ew = new QueryWrapper<SysUserRole>();
		ew.eq("user_id ", id);
		List<SysUserRole> mySysUserRoles = sysUserRoleService.list(ew);
		List<Long> myRolds = Lists.transform(mySysUserRoles, input -> input.getRoleId());

		model.addAttribute("sysUser", sysUser);
		model.addAttribute("sysRoles", sysRoles);
		model.addAttribute("myRolds", myRolds);
		model.addAttribute("deptList", sysDeptService.list(null));
		return "system/user/edit";
	}

	/**
	 * 执行编辑
	 */
	@Log("编辑用户")
	@RequiresPermissions("editUser")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(SysUser sysUser, @RequestParam(value = "roleId[]", required = false) Long[] roleId) {
		sysUserService.updateUser(sysUser, roleId);
		return Rest.ok();
	}

	/**
	 * 验证用户名是否已存在
	 */
	@RequestMapping("/checkName")
	@ResponseBody
	public boolean checkName(String userName) {
		List<SysUser> list = sysUserService.list(new QueryWrapper<SysUser>().eq("user_name", userName));
		if (list.size() > 0) {
			return false;
		}
		return true;
	}
}
