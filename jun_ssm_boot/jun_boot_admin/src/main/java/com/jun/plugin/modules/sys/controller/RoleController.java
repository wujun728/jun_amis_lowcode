package com.jun.plugin.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.PageUtil;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.text.Convert;
import com.jun.plugin.common.vo.PageResultVo;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.config.shiro.MyShiroRealm;
import com.jun.plugin.modules.sys.model.SysMenu;
import com.jun.plugin.modules.sys.model.SysRole;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.service.MenuService;
import com.jun.plugin.modules.sys.service.RoleService;
import com.jun.plugin.modules.sys.service.UserService;
import com.jun.plugin.modules.sys.vo.MenuTreeListVo;

@Controller
@RequestMapping("/role")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private UserService userService;

	@Autowired
	private MyShiroRealm myShiroRealm;

	/* 角色列表数据 */
	@PostMapping("/list")
	@ResponseBody
	public PageResultVo pageRoles(SysRole role, Integer limit, Integer offset) {
		try {
			PageHelper.startPage(PageUtil.getPageNo(limit, offset), limit);
			List<SysRole> roleList = roleService.listRoles(role);
			PageInfo<SysRole> pages = new PageInfo<>(roleList);
			return ResultUtil.table(roleList, pages.getTotal());
		} catch (Exception e) {
			logger.error(String.format("RoleController.loadRoles%s", e));
			throw e;
		}
	}

	/* 新增角色 */
	@GetMapping("/add")
	public String add() {
		return "role/add";
	}

	/* 新增角色 */
	@PostMapping("/doAdd")
	@ResponseBody
	public ResponseVo<?> addRole(SysRole role) {
		try {
			int insertResult = roleService.save(role);
			if (insertResult > 0) {
				return ResultUtil.success("添加角色成功");
			} else {
				return ResultUtil.error("添加角色失败");
			}
		} catch (Exception e) {
			logger.error(String.format("RoleController.addRole%s", e));
			throw e;
		}
	}

	/* 编辑角色详情 */
	@GetMapping("/edit")
	public String detail(Model model, String roleId) {
		SysRole role = roleService.getSysRoleById(Long.valueOf(roleId));
		model.addAttribute("role", role);
		return "role/detail";
	}

	/* 编辑角色 */
	@PostMapping("/edit")
	@ResponseBody
	public ResponseVo<?> editRole(@ModelAttribute("role") SysRole role) {
		int a = roleService.updateByRoleId(role);
		if (a > 0) {
			return ResultUtil.success("编辑角色成功");
		} else {
			return ResultUtil.error("编辑角色失败");
		}
	}

	/* 删除角色 */
	@GetMapping("/delete")
	@ResponseBody
	public ResponseVo<?> deleteRole(String roleId) {
		Long[] roleIds = { Long.valueOf(roleId) };
		if (roleService.listAllUsersByRoleIds(roleIds).size() > 0) {
			return ResultUtil.error("删除失败,该角色下存在用户");
		}
		Long[] roleIdsList = { Long.valueOf(roleId) };
		int updateResult = roleService.updateStatusBatch(roleIdsList, CoreConst.STATUS_INVALID);
		if (updateResult > 0) {
			return ResultUtil.success("删除角色成功");
		} else {
			return ResultUtil.error("删除角色失败");
		}
	}

	@GetMapping("/batch/delete")
	@ResponseBody
	public ResponseVo<?> batchDeleteRole(String roleIdStr) {
		Long[] roleIdsList = Convert.toLongArray(",", roleIdStr);
		if (roleService.listAllUsersByRoleIds(roleIdsList).size() > 0) {
			return ResultUtil.error("删除失败,选择的角色下存在用户");
		}
		int updateBatchResult = roleService.updateStatusBatch(roleIdsList, CoreConst.STATUS_INVALID);
		if (updateBatchResult > 0) {
			return ResultUtil.success("删除角色成功");
		} else {
			return ResultUtil.error("删除角色失败");
		}
	}

	/* 分配权限详情 */
	@GetMapping("/assignMenus")
	public String assignMenus(Model model, String roleId) {
		model.addAttribute("roleId", roleId);
		return "role/assignmenus";
	}

	/* 分配权限列表查询 */
	@PostMapping("/assign/menu/list")
	@ResponseBody
	public List<MenuTreeListVo> assignRole(String roleId) {
		List<MenuTreeListVo> listVos = new ArrayList<>();
		List<SysMenu> allMenus = menuService.listAllPermsByStatus(CoreConst.STATUS_VALID);
		List<SysMenu> hasMenus = roleService.listMenusByRoleId(Long.valueOf(roleId));
		for (SysMenu menu : allMenus) {
			MenuTreeListVo vo = new MenuTreeListVo();
			vo.setMenuId(menu.getMenuId());
			vo.setName(menu.getName());
			vo.setParentId(menu.getParentId());
			for (SysMenu hasMenu : hasMenus) {
				// 有权限则选中
				if (hasMenu.getMenuId().equals(menu.getMenuId())) {
					vo.setChecked(true);
					break;
				}
			}
			listVos.add(vo);
		}
		return listVos;
	}

	/* 分配权限 */
	@PostMapping("/assign/menu")
	@ResponseBody
	public ResponseVo<?> assignRole(String roleId, String menuIdStr) {
		List<Long> menuIdsList = new ArrayList<>();
		if (StringUtils.isNotBlank(menuIdStr)) {
			Long[] menuIdArray = Convert.toLongArray(",", menuIdStr);
			menuIdsList = Arrays.asList(menuIdArray);
		}
		ResponseVo<?> responseVo = roleService.addAssignMenu(Long.valueOf(roleId), menuIdsList);
		myShiroRealm.clearCachedAuthorizationInfo();
		return responseVo;
	}

	/* 分配角色详情 */
	@GetMapping("/assignUsers")
	public String assignUsers(Model model, String roleId) {
		model.addAttribute("roleId", roleId);
		return "role/assignusers";
	}

	/* 分配角色列表查询 */
	@PostMapping("/assign/user/list")
	@ResponseBody
	public Map<String, Object> assignUser(String roleId) {
		List<SysUser> userList = userService.listUsers(new SysUser());
		Set<String> hasUsers = roleService.listUserIdsByRoleId(Long.valueOf(roleId));
		Map<String, Object> jsonMap = new HashMap<>(2);
		jsonMap.put("rows", userList);
		jsonMap.put("hasUsers", hasUsers);
		return jsonMap;
	}

	/* 取消分配 */
	@PostMapping("/cancelAssign")
	@ResponseBody
	public ResponseVo<?> cancelAssign(String roleId, String userId) {
		ResponseVo<?> responseVo = roleService.cancelAssign(Long.valueOf(roleId), Long.valueOf(userId));
		return responseVo;
	}
	
	/* 取消分配 */
	@PostMapping("/assignUser")
	@ResponseBody
	public ResponseVo<?> assignUser(String roleId, String userId) {
		ResponseVo<?> responseVo = roleService.assignUser(Long.valueOf(roleId), Long.valueOf(userId));
		return responseVo;
	}

}
