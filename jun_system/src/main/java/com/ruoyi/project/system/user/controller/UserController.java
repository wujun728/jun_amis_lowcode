package com.ruoyi.project.system.user.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.poi.ExcelUtilx;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.post.service.PostService;
import com.ruoyi.project.system.role.service.RoleService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
	private String prefix = "system/user";

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PostService postService;

	@RequiresPermissions("system:user:view")
	@GetMapping()
	public String user() {
		return prefix + "/user";
	}

	@RequiresPermissions("system:user:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HttpServletRequest request) {
        return userService.selectUserList(request, true);
	}

	@Log(title = "用户管理", businessType = BusinessType.EXPORT)
	@RequiresPermissions("system:user:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(HttpServletRequest request) {
    	//定义表头
    	Map<String, String> headers = new LinkedHashMap<String, String>();
    	headers.put("user_id", "用户序号");
    	headers.put("login_name", "登录名称");
    	headers.put("user_name", "用户名称");
    	headers.put("email", "用户邮箱");
    	headers.put("phonenumber", "手机号码");
    	headers.put("sex_name", "用户性别");
    	headers.put("status_name", "帐号状态");
    	headers.put("login_ip", "最后登录IP");
    	headers.put("login_date", "最后登录时间");
    	headers.put("dept_name", "部门名称");

		//数据集合
		List<?> dataList = userService.selectUserList(request, false).getRows();

		return ExcelUtilx.exportExcel(headers, dataList, "用户数据");
	}

//	@Log(title = "用户管理", businessType = BusinessType.IMPORT)
//	@RequiresPermissions("system:user:import")
//	@PostMapping("/importData")
//	@ResponseBody
//	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
//		ExcelUtil<User> util = new ExcelUtil<User>(User.class);
//		List<User> userList = util.importExcel(file.getInputStream());
//		String message = iuserService.importUser(userList, updateSupport);
//		return AjaxResult.success(message);
//	}

//	@RequiresPermissions("system:user:view")
//	@GetMapping("/importTemplate")
//	@ResponseBody
//	public AjaxResult importTemplate() {
//		ExcelUtil<User> util = new ExcelUtil<User>(User.class);
//		return util.importTemplateExcel("用户数据");
//	}

	/**
	 * 新增用户
	 */
	@GetMapping("/add")
	public String add(HttpServletRequest request, ModelMap mmap) {
		mmap.put("roleList", JSONUtil.toJsonStr(roleService.selectRoleAll()));
		mmap.put("postList", JSONUtil.toJsonStr(postService.selectPostAll()));
		return prefix + "/add";
	}

	/**
	 * 新增保存用户
	 */
	@RequiresPermissions("system:user:add")
	@Log(title = "用户管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HttpServletRequest request) {
        String loginName = RequestUtil.getValue(request, "login_name");
        String phonenumber = RequestUtil.getValue(request, "phonenumber");
        String email = RequestUtil.getValue(request, "email");
        if (userService.checkLoginNameUnique(request) > 0) {
            return error("新增用户'" + loginName + "'失败，登录账号已存在");
        } else if (StrUtil.isNotEmpty(phonenumber) && userService.checkPhoneUnique(phonenumber, "") > 0) {
            return error("新增用户'" + loginName + "'失败，手机号码已存在");
        } else if (StrUtil.isNotEmpty(email) && userService.checkEmailUnique(email, "") > 0) {
            return error("新增用户'" + loginName + "'失败，邮箱账号已存在");
        }
        return toAjax(userService.insertUser(request));
	}

	/**
	 * 修改用户
	 */
	@GetMapping("/edit/{user_id}")
	public String edit(@PathVariable("user_id") String user_id, ModelMap mmap) {
		mmap.put("user", userService.selectUserById(user_id));
        mmap.put("roleList", JSONUtil.toJsonStr(roleService.selectRoleAll()));
        mmap.put("postList", JSONUtil.toJsonStr(postService.selectPostAll()));
		return prefix + "/edit";
	}

	/**
	 * 修改保存用户
	 */
	@RequiresPermissions("system:user:edit")
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HttpServletRequest request) {
		String userId = RequestUtil.getValue(request, "user_id");
		String loginName = RequestUtil.getValue(request, "login_name");
        String phonenumber = RequestUtil.getValue(request, "phonenumber");
        String email = RequestUtil.getValue(request, "email");

        if (StrUtil.isNotBlank(userId) && User.isAdmin(Long.parseLong(userId))) {
            return error("不允许修改超级管理员用户！");
        }
        else if (StrUtil.isNotEmpty(phonenumber) && userService.checkPhoneUnique(phonenumber, userId) > 0) {
            return error("修改用户'" + loginName + "'失败，手机号码已存在");
        } else if (StrUtil.isNotEmpty(email) && userService.checkPhoneUnique(email, userId) > 0) {
            return error("修改用户'" + loginName + "'失败，邮箱账号已存在");
        }
        return toAjax(userService.updateUser(request));
	}

	@RequiresPermissions("system:user:resetPwd")
	@GetMapping("/resetPwd/{user_id}")
	public String resetPwd(@PathVariable("user_id") String user_id, ModelMap mmap) {
		mmap.put("user", userService.selectUserById(user_id));
		return prefix + "/resetPwd";
	}

	@RequiresPermissions("system:user:resetPwd")
	@Log(title = "重置密码", businessType = BusinessType.UPDATE)
	@PostMapping("/resetPwd")
	@ResponseBody
	public AjaxResult resetPwdSave(HttpServletRequest request) {
		String user_id = RequestUtil.getValue(request, "user_id");
		if (userService.resetUserPwd(request) > 0) {
			if (ShiroUtils.getUserId().equals(user_id)) {
				setSysUser(userService.selectUserById(user_id));
			}
			return success();
		}
		return error();
	}

	@RequiresPermissions("system:user:remove")
	@Log(title = "用户管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
        return toAjax(userService.deleteUserByIds(ids));
	}

	/**
	 * 校验用户名
	 */
	@PostMapping("/checkLoginNameUnique")
	@ResponseBody
	public int checkLoginNameUnique(HttpServletRequest request) {
		return userService.checkLoginNameUnique(request);
	}

	/**
	 * 校验手机号码
	 */
	@PostMapping("/checkPhoneUnique")
	@ResponseBody
	public int checkPhoneUnique(HttpServletRequest request) {
        String userId = RequestUtil.getValue(request, "user_id");
        String phonenumber = RequestUtil.getValue(request, "phonenumber");
		return userService.checkPhoneUnique(phonenumber, userId);
	}

	/**
	 * 校验email邮箱
	 */
	@PostMapping("/checkEmailUnique")
	@ResponseBody
	public int checkEmailUnique(HttpServletRequest request) {
        String userId = RequestUtil.getValue(request, "user_id");
        String email = RequestUtil.getValue(request, "email");
		return userService.checkEmailUnique(email, userId);
	}

	/**
	 * 用户状态修改
	 */
	@Log(title = "用户管理", businessType = BusinessType.UPDATE)
	@RequiresPermissions("system:user:edit")
	@PostMapping("/changeStatus")
	@ResponseBody
	public AjaxResult changeStatus(HttpServletRequest request) {
		return toAjax(userService.changeStatus(request));
	}
}