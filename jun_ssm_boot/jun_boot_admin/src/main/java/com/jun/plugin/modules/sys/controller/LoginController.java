package com.jun.plugin.modules.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.PasswordHelper;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.ServletUtils;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.config.shiro.ShiroUtils;
import com.jun.plugin.modules.sys.model.SysMenu;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.service.MenuService;
import com.jun.plugin.modules.sys.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;

	/* 首页 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap modelMap) {
		SysUser user = ShiroUtils.getUser();
		List<SysMenu> menus = menuService.listMenusByUser(user);
		modelMap.put("menus", menus);
		modelMap.put("user", user);
		return "index";
	}

	/* 注册 */
	@GetMapping("/register")
	public String register() {
		return "register";
	}

	/* 提交注册 */
	@PostMapping("/register")
	@ResponseBody
	public ResponseVo<?> register(HttpServletRequest request, SysUser registerUser, String confirmPassword, String verification) {
		// 判断验证码
		String rightCode = (String) ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
			// 验证码通过
		} else {
			return ResultUtil.error("验证码错误！");
		}
		String username = registerUser.getUsername();
		SysUser user = userService.getUserByUsername(username);
		if (null != user) {
			return ResultUtil.error("用户名已存在！");
		}
		String password = registerUser.getPassword();
		// 判断两次输入密码是否相等
		if (confirmPassword != null && password != null) {
			if (!confirmPassword.equals(password)) {
				return ResultUtil.error("两次密码不一致！");
			}
		}
		registerUser.setUserId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
		registerUser.setStatus(CoreConst.STATUS_VALID);
		Date date = new Date();
		registerUser.setCreateTime(date);
		registerUser.setUpdateTime(date);
		registerUser.setLastLoginTime(date);
		PasswordHelper.encryptPassword(registerUser);
		// 注册
		int registerResult = userService.register(registerUser);
		if (registerResult > 0) {
			return ResultUtil.success("注册成功！");
		} else {
			return ResultUtil.error("注册失败，请稍后再试！");
		}
	}

	/* 登陆 */
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		// 如果是Ajax请求，返回Json字符串。
		if (ServletUtils.isAjaxRequest(request)) {
			return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
		}
		return "login";
	}

	/* 提交登录 */
	@PostMapping("/login")
	@ResponseBody
	public ResponseVo<?> login(HttpServletRequest request, String username, String password, String validateCode, Boolean rememberMe) {

		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		String rightCode = (String) ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (rightCode == null || "".equals(rightCode)) {
			return ResultUtil.error("验证码失效！");
		}
		if (!(StringUtils.isNotBlank(validateCode) && validateCode.equals(rightCode))) {
			return ResultUtil.error("验证码错误！");
		}
		ShiroUtils.removeSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
		try {
			ShiroUtils.getSubject().login(token);
		} catch (LockedAccountException e) {
			token.clear();
			return ResultUtil.error("用户已经被锁定不能登录，请联系管理员！");
		} catch (AuthenticationException e) {
			token.clear();
			return ResultUtil.error("用户名或者密码错误！");
		}
		// 更新最后登录时间
		userService.updateLastLoginTimeByUser(ShiroUtils.getUser());
		return ResultUtil.success("登录成功！");
	}

	/* 踢出 */
	@GetMapping("/kickout")
	public String kickout() {
		return "kickout";
	}

}
