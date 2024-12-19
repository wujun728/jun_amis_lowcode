package com.ruoyi.project.system.role.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ruoyi.common.utils.security.AuthorizationUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.poi.ExcelUtilx;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.role.service.RoleService;
import com.ruoyi.project.system.user.service.UserService;

/**
 * 角色信息
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	private String prefix = "system/role";

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@RequiresPermissions("system:role:view")
	@GetMapping()
	public String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("system:role:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HttpServletRequest request) {
		return roleService.selectRoleList(request, true);
	}

	@Log(title = "角色管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("system:role:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(HttpServletRequest request) {
		//定义表头
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("role_id", "角色编号");
		headers.put("role_name", "角色名称");
		headers.put("role_key", "权限字符");
		headers.put("role_sort", "显示顺序");
		headers.put("data_scope_name", "数据范围");
		headers.put("status_name", "状态");
		headers.put("create_time", "创建时间");
		headers.put("remark", "备注");

		//数据集合
		List<?> dataList = roleService.selectRoleList(request, false).getRows();

		return ExcelUtilx.exportExcel(headers, dataList, "角色数据");
	}

	/**
	 * 新增角色
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 修改角色
	 */
	@GetMapping("/edit/{role_id}")
	public String edit(@PathVariable("role_id") String role_id, ModelMap mmap) {
		mmap.put("role", roleService.selectRoleById(role_id));
		return prefix + "/edit";
	}

	/**
	 * 新增保存角色
	 */
	@RequiresPermissions("system:role:add")
	@Log(title = "角色管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HttpServletRequest request) {
		String role_name = RequestUtil.getValue(request, "role_name");
		if (roleService.checkRoleNameUnique(request) > 0) {
			return error("新增角色'" + role_name + "'失败，角色名称已存在！");
		} else if (roleService.checkRoleKeyUnique(request) > 0) {
			return error("新增角色'" + role_name + "'失败，角色权限已存在！");
		}
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
		return toAjax(roleService.insertRole(request));

	}

	/**
	 * 修改保存角色
	 */
	@RequiresPermissions("system:role:edit")
	@Log(title = "角色管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HttpServletRequest request) {
		String role_id = RequestUtil.getValue(request, "role_id");
		String role_name = RequestUtil.getValue(request, "role_name");
		roleService.checkRoleAllowed(roleService.selectRoleById(role_id));
		if (roleService.checkRoleNameUnique(request) > 0) {
			return error("修改角色'" + role_name + "'失败，角色名称已存在！");
		} else if (roleService.checkRoleKeyUnique(request) > 0) {
			return error("修改角色'" + role_name + "'失败，角色权限已存在！");
		}
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
		return toAjax(roleService.updateRole(request));
	}

	/**
	 * 角色分配数据权限
	 */
	@GetMapping("/authDataScope/{role_id}")
	public String authDataScope(@PathVariable("role_id") String role_id, ModelMap mmap) {
		mmap.put("role", roleService.selectRoleById(role_id));
		return prefix + "/dataScope";
	}

	/**
	 * 保存角色分配数据权限
	 */
	@RequiresPermissions("system:role:edit")
	@Log(title = "角色管理", businessType = BusinessType.GRANT)
	@PostMapping("/authDataScope")
	@ResponseBody
	public AjaxResult authDataScopeSave(HttpServletRequest request) {
		String role_id = RequestUtil.getValue(request, "role_id");
		roleService.checkRoleAllowed(roleService.selectRoleById(role_id));
		if (roleService.authDataScope(request) > 0) {
			setSysUser(userService.selectUserById(getSysUser().getUserId()));
			return success();
		}
		return error();
	}

	@RequiresPermissions("system:role:remove")
	@Log(title = "角色管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
        return toAjax(roleService.deleteRoleByIds(ids));
	}

	/**
	 * 校验角色名称
	 */
	@PostMapping("/checkRoleNameUnique")
	@ResponseBody
	public int checkRoleNameUnique(HttpServletRequest request) {
		return roleService.checkRoleNameUnique(request);
	}

	/**
	 * 校验角色权限
	 */
	@PostMapping("/checkRoleKeyUnique")
	@ResponseBody
	public int checkRoleKeyUnique(HttpServletRequest request) {
		return roleService.checkRoleKeyUnique(request);
	}

	/**
	 * 选择菜单树
	 */
	@GetMapping("/selectMenuTree")
	public String selectMenuTree() {
		return prefix + "/tree";
	}

	/**
	 * 角色状态修改
	 */
	@Log(title = "角色管理", businessType = BusinessType.UPDATE)
	@RequiresPermissions("system:role:edit")
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(HttpServletRequest request) {
		String role_id = RequestUtil.getValue(request, "role_id");
		roleService.checkRoleAllowed(roleService.selectRoleById(role_id));
		return toAjax(roleService.changeStatus(request));
	}

	/**
	 * 分配用户
	 */
	@RequiresPermissions("system:role:edit")
	@GetMapping("/authUser/{role_id}")
	public String authUser(@PathVariable("role_id") String role_id, ModelMap mmap) {
		mmap.put("role", roleService.selectRoleById(role_id));
		return prefix + "/authUser";
	}

	/**
	 * 查询已分配用户角色列表
	 */
	@RequiresPermissions("system:role:list")
	@PostMapping("/authUser/allocatedList")
	@ResponseBody
	public TableDataInfo allocatedList(HttpServletRequest request) {
		return userService.selectAllocatedList(request);
	}

	/**
	 * 批量取消授权
	 */
	@Log(title = "角色管理", businessType = BusinessType.GRANT)
	@PostMapping("/authUser/cancelAll")
	@ResponseBody
	public AjaxResult cancelAuthUserAll(String role_id, String userIds) {
		return toAjax(roleService.deleteAuthUsers(role_id, userIds));
	}

	/**
	 * 选择用户
	 */
	@GetMapping("/authUser/selectUser/{role_id}")
	public String selectUser(@PathVariable("role_id") String role_id, ModelMap mmap) {
		mmap.put("role", roleService.selectRoleById(role_id));
		return prefix + "/selectUser";
	}

	/**
	 * 查询未分配用户角色列表
	 */
	@RequiresPermissions("system:role:list")
	@PostMapping("/authUser/unallocatedList")
	@ResponseBody
	public TableDataInfo unallocatedList(HttpServletRequest request) {
		return userService.selectUnallocatedList(request);
	}

	/**
	 * 批量选择用户授权
	 */
	@Log(title = "角色管理", businessType = BusinessType.GRANT)
	@PostMapping("/authUser/selectAll")
	@ResponseBody
	public AjaxResult selectAuthUserAll(String role_id, String userIds) {
		return toAjax(roleService.insertAuthUsers(role_id, userIds));
	}
}