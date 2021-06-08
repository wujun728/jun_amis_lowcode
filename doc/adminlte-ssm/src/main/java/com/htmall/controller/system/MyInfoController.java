package com.htmall.controller.system;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.htmall.common.controller.BaseController;
import com.htmall.common.util.ShiroUtil;
import com.htmall.entity.SysUser;
import com.htmall.service.ISysUserService;

@Controller
@RequestMapping("/system/myinfo")
public class MyInfoController extends BaseController {

	@Autowired
	private ISysUserService sysUserService;

	/**
	 * 个人信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/info")
	public String info(Model model) {
		SysUser sysUser = sysUserService.getById(ShiroUtil.getSessionUid());
		model.addAttribute("sysUser", sysUser);
		return "system/myinfo/info";
	}

	/**
	 * 修改密码页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/pwd")
	public String pwd(Model model) {
		return "system/myinfo/pwd";
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/doChangePwd")
	public String doChangePwd(String password, String newpassword, String newpassword2, Model model, RedirectAttributes redirectAttributes) {

		if (StringUtils.isBlank(password) || StringUtils.isBlank(newpassword) || StringUtils.isBlank(newpassword2)) {
			redirectAttributes.addFlashAttribute("msg", "客户端提交数据不能为空.");
			return redirectTo("/system/myinfo/pwd");
		}

		Subject subject = SecurityUtils.getSubject();
		SysUser sysUser = (SysUser) subject.getPrincipal();

		SysUser user = sysUserService.getById(sysUser.getId());
		if (!user.getPassword().equals(new SimpleHash("MD5", password, sysUser.getUserName(), 1024).toString())) {
			redirectAttributes.addFlashAttribute("msg", "旧密码输入错误.");
			return redirectTo("/system/myinfo/pwd");
		}

		if (!newpassword2.equals(newpassword)) {
			redirectAttributes.addFlashAttribute("msg", "两次输入的密码不一致.");
			return redirectTo("/system/myinfo/pwd");
		}

		user.setPassword(new SimpleHash("MD5", newpassword, sysUser.getUserName(), 1024).toString());
		sysUserService.updateById(user);

		redirectAttributes.addFlashAttribute("info", "密码修改成功.");
		return redirectTo("/system/myinfo/pwd");
	}

	/**
	 * 更新用户
	 * 
	 * @param sysUser
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(SysUser sysUser, Model model, RedirectAttributes redirectAttributes) {

		SysUser user = sysUserService.getById(sysUser.getId());
		if (StringUtils.isNotBlank(user.getUserImg())) {
			user.setUserImg(sysUser.getUserImg());
		}
		sysUserService.updateById(user);
		model.addAttribute("sysUser", user);
		redirectAttributes.addFlashAttribute("info", "更新成功.");
		return redirectTo("/system/myinfo/info");
	}
}
