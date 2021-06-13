package com.htmall.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.htmall.common.controller.BaseController;
import com.htmall.common.util.ShiroUtil;
import com.htmall.entity.SysUser;
import com.htmall.service.ISysLogService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	/**
	 * 日志服务
	 */
	@Autowired
	private ISysLogService sysLogService;

	@Autowired
	private Producer captchaProducer;

	/**
	 * 登录页面
	 */
	@RequestMapping
	public String login(Model model) {
		return "login";
	}

	/**
	 * 执行登录
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(String userName, String password, String captcha, String return_url, RedirectAttributesModelMap model) {

		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		String rightCode = (String) ShiroUtil.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (rightCode == null || "".equals(rightCode)) {
			model.addFlashAttribute("error", "验证码失效！");
			return redirectTo("/login");
		}
		if (!(StringUtils.isNotBlank(captcha) && captcha.equals(rightCode))) {
			model.addFlashAttribute("error", "验证码错误！");
			return redirectTo("/login");
		}
		ShiroUtil.removeSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!currentUser.isAuthenticated()) {
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				model.addFlashAttribute("error", "未知用户");
				return redirectTo("/login");
			} catch (IncorrectCredentialsException ice) {
				model.addFlashAttribute("error", "密码错误");
				return redirectTo("/login");
			} catch (LockedAccountException lae) {
				model.addFlashAttribute("error", "账号已锁定");
				return redirectTo("/login");
			} catch (AuthenticationException ae) {
				model.addFlashAttribute("error", "服务器繁忙");
				return redirectTo("/login");
			}
		}
		// 记录登录日志
		Subject subject = SecurityUtils.getSubject();
		SysUser sysUser = (SysUser) subject.getPrincipal();
		sysLogService.insertLog("用户登录成功", sysUser.getUserName(), request.getRequestURI(), "");
		return redirectTo("/");
	}

	/**
	 * 验证码
	 */
	@RequestMapping("/captcha")
	@ResponseBody
	public void captcha() throws ServletException, IOException {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		// 生成验证码文本
		String capText = captchaProducer.createText();
		String capStr = capText.substring(0, capText.lastIndexOf("@"));
		String code = capText.substring(capText.lastIndexOf("@") + 1);
		ShiroUtil.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, code);
		logger.info("生成验证码文本====" + capText);
		// 利用生成的字符串构建图片
		BufferedImage bi = captchaProducer.createImage(capStr);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}
}
