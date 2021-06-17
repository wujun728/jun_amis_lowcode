package com.jun.plugin.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.CopyUtil;
import com.jun.plugin.common.util.PageUtil;
import com.jun.plugin.common.util.PasswordHelper;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.util.poi.ExcelUtil;
import com.jun.plugin.common.util.text.Convert;
import com.jun.plugin.common.vo.PageResultVo;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.config.shiro.MyShiroRealm;
import com.jun.plugin.config.shiro.ShiroUtils;
import com.jun.plugin.modules.sys.model.SysRole;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.service.RoleService;
import com.jun.plugin.modules.sys.service.UserService;
import com.jun.plugin.modules.sys.vo.ChangePasswordVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MyShiroRealm myShiroRealm;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/** 用户列表数据 */
	@PostMapping("/list")
	@ResponseBody
	public PageResultVo loadUsers(SysUser user, Integer limit, Integer offset) {
		PageHelper.startPage(PageUtil.getPageNo(limit, offset), limit);
		List<SysUser> userList = userService.listUsers(user);
		PageInfo<SysUser> pages = new PageInfo<>(userList);
		return ResultUtil.table(userList, pages.getTotal());
	}

	/** 新增用户 */
	@GetMapping("/add")
	public String add() {
		return "user/add";
	}

	/** 新增用户 */
	@PostMapping("/doAdd")
	@ResponseBody
	public ResponseVo<?> add(SysUser userForm, String confirmPassword) {
		String username = userForm.getUsername();
		SysUser user = userService.getUserByUsername(username);
		if (null != user) {
			return ResultUtil.error("用户名已存在");
		}
		String password = userForm.getPassword();
		// 判断两次输入密码是否相等
		if (confirmPassword != null && password != null) {
			if (!confirmPassword.equals(password)) {
				return ResultUtil.error("两次密码不一致");
			}
		}
		userForm.setUserId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
		userForm.setStatus(CoreConst.STATUS_VALID);
		Date date = new Date();
		userForm.setCreateTime(date);
		userForm.setUpdateTime(date);
		userForm.setLastLoginTime(date);
		PasswordHelper.encryptPassword(userForm);
		int num = userService.register(userForm);
		if (num > 0) {
			return ResultUtil.success("添加用户成功");
		} else {
			return ResultUtil.error("添加用户失败");
		}
	}

	/** 编辑用户详情 */
	@GetMapping("/edit")
	public String userDetail(Model model, String userId) {
		SysUser user = userService.getUserByUserId(Long.valueOf(userId));
		model.addAttribute("user", user);
		return "user/detail";
	}

	/** 编辑用户 */
	@PostMapping("/edit")
	@ResponseBody
	public ResponseVo<?> editUser(SysUser sysUser) {
		int a = userService.updateUserByUserId(sysUser);
		if (a > 0) {
			return ResultUtil.success("编辑用户成功！");
		} else {
			return ResultUtil.error("编辑用户失败");
		}
	}

	/** 删除用户 */
	@GetMapping("/delete")
	@ResponseBody
	public ResponseVo<?> deleteUser(String userId) {
		List<String> userIdsList = Arrays.asList(userId);
		int result = userService.updateUserStatusByUserIds(userIdsList, CoreConst.STATUS_INVALID);
		if (result > 0) {
			return ResultUtil.success("删除用户成功");
		} else {
			return ResultUtil.error("删除用户失败");
		}
	}

	@GetMapping("/batch/delete")
	@ResponseBody
	public ResponseVo<?> batchDeleteUser(String userIdStr) {
		String[] userIds = userIdStr.split(",");
		List<String> userIdsList = Arrays.asList(userIds);
		int result = userService.updateUserStatusByUserIds(userIdsList, CoreConst.STATUS_INVALID);
		if (result > 0) {
			return ResultUtil.success("删除用户成功");
		} else {
			return ResultUtil.error("删除用户失败");
		}
	}

	/** 编辑用户详情 */
	@GetMapping("/assign")
	public String assign(Model model, String userId) {
		model.addAttribute("userId", userId);
		return "user/assign";
	}

	/** 分配角色列表查询 */
	@PostMapping("/assign/role/list")
	@ResponseBody
	public Map<String, Object> assignRoleList(String userId) {
		// 查询所有角色
		List<SysRole> roleList = roleService.listRoles(new SysRole());
		Set<String> hasRoles = roleService.listRoleIdsByUserId(Long.valueOf(userId));
		Map<String, Object> jsonMap = new HashMap<>(2);
		jsonMap.put("rows", roleList);
		jsonMap.put("hasRoles", hasRoles);
		return jsonMap;
	}

	/** 保存分配角色 */
	@PostMapping("/assign/role")
	@ResponseBody
	public ResponseVo<?> assignRole(String userId, String roleIdStr) {
		Long[] roleIdsList = Convert.toLongArray(",", roleIdStr);
		ResponseVo<?> responseVo = userService.addAssignRole(Long.valueOf(userId), roleIdsList);
		List<Long> userIds = new ArrayList<>();
		userIds.add(Long.valueOf(userId));
		myShiroRealm.clearCachedAuthorizationInfo();
		return responseVo;
	}

	/** 编辑用户详情 */
	@GetMapping("/updatePwd")
	public String updatePwd() {
		return "ucenter/pwd";
	}

	/* 修改密码 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo<?> changePassword(ChangePasswordVo changePasswordVo) {
		if (!changePasswordVo.getNewPassword().equals(changePasswordVo.getConfirmNewPassword())) {
			return ResultUtil.error("两次密码输入不一致");
		}
		SysUser loginUser = userService.getUserByUserId(ShiroUtils.getUserId());
		SysUser newUser = CopyUtil.getCopy(loginUser, SysUser.class);
		String sysOldPassword = loginUser.getPassword();
		newUser.setPassword(changePasswordVo.getOldPassword());
		String entryOldPassword = PasswordHelper.getPassword(newUser);
		if (sysOldPassword.equals(entryOldPassword)) {
			newUser.setPassword(changePasswordVo.getNewPassword());
			PasswordHelper.encryptPassword(newUser);
			userService.updateUserByUserId(newUser);
			// *清除登录缓存*//
			myShiroRealm.clearCachedAuthorizationInfo();
		} else {
			return ResultUtil.error("您输入的旧密码有误");
		}
		return ResultUtil.success("修改密码成功");
	}

	@PostMapping("/export")
	@ResponseBody
	public ResponseVo<?> export(SysUser user) {
		List<SysUser> list = userService.listUsers(user);
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
		return util.exportExcel(list, "用户数据");
	}

	@PostMapping("/importData")
	@ResponseBody
	public ResponseVo<?> importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
		List<SysUser> userList = util.importExcel(file.getInputStream());
		String operName = ShiroUtils.getUser().getUsername();
		String message = userService.importUser(userList, updateSupport, operName);
		return ResultUtil.success(message);
	}

	@GetMapping("/importTemplate")
	@ResponseBody
	public ResponseVo<?> importTemplate() {
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
		return util.importTemplateExcel("用户数据");
	}

}
