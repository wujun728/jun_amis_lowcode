package com.qingshop.mall.modules.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.framework.shiro.ShiroUtils;
import com.qingshop.mall.modules.common.BaseController;
import com.qingshop.mall.modules.system.entity.SysUser;
import com.qingshop.mall.modules.system.service.ISysUserService;

/**
 * 用户中心控制器
 */
@Controller
@RequestMapping("/system/me")
public class MeController extends BaseController {

	@Autowired
	private ISysUserService sysUserService;

	/**
	 * 个人信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/info")
	public String info(Model model) {

		SysUser sysUser = sysUserService.getById(ShiroUtils.getUserId());
		model.addAttribute("sysUser", sysUser);
		return "system/me/info";
	}

	/**
	 * 修改密码页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/pwd")
	public String pwd(Model model) {
		return "system/me/pwd";
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/doChangePwd")
	@ResponseBody
	public Rest doChangePwd(String password, String newpassword, String newpassword2) {

		if (StringUtils.isBlank(password) || StringUtils.isBlank(newpassword) || StringUtils.isBlank(newpassword2)) {
			return Rest.failure("客户端提交数据不能为空");
		}

		SysUser sysUser = ShiroUtils.getSysUser();

		SysUser user = sysUserService.getById(sysUser.getUserId());
		if (!user.getPassword().equals(ShiroUtils.md51024Pwd(password, user.getUserName()))) {
			return Rest.failure("旧密码输入错误.");
		}

		if (!newpassword2.equals(newpassword)) {
			return Rest.failure("两次输入的密码不一致.");
		}

		// 加密新密码
		user.setPassword(ShiroUtils.md51024Pwd(newpassword, user.getUserName()));
		sysUserService.updateById(user);
		return Rest.ok();
	}

	/**
	 * 更新用户
	 * 
	 * @param sysUser
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public Rest updateUser(SysUser sysUser) {

		SysUser user = sysUserService.getById(sysUser.getUserId());
		if (StringUtils.isNotBlank(user.getUserImg())) {
			user.setUserImg(sysUser.getUserImg());
		}

		sysUserService.updateById(user);
		return Rest.ok();
	}
}
