package com.qingshop.mall.modules.common;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.qingshop.mall.framework.shiro.ShiroUtils;
import com.qingshop.mall.modules.system.entity.SysUser;
import com.qingshop.mall.modules.system.service.ISysLogService;

/**
 * 登录控制器
 * 
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private Producer producerMath;

	/**
	 * 日志服务
	 */
	@Autowired
	private ISysLogService sysLogService;

	/**
	 * 登录页面
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/unauth")
	public String unauth() {
		return "error/unauth";
	}

	/**
	 * 执行登录
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(String userName, String password, String captcha, String return_url, RedirectAttributesModelMap model, HttpServletRequest request) {

		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);

		if (kaptcha == null) {
			model.addFlashAttribute("error", "验证码已失效");
			return redirectTo("/login");
		}

		if (!captcha.equalsIgnoreCase(kaptcha)) {
			model.addFlashAttribute("error", "验证码错误");
			return redirectTo("/login");
		}

		Subject currentUser = ShiroUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
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
		ShiroUtils.setSessionAttribute("sessionFlag", true);
		// 记录登录日志
		SysUser sysUser = ShiroUtils.getSysUser();
		sysLogService.insertLog("用户登录成功", sysUser.getUserName(), request.getRequestURI(), "");
		return redirectTo("/index");
	}

	/**
	 * 验证码
	 */
	@RequestMapping("/captcha")
	public ModelAndView captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		// 生成验证码文本
		String capText = producerMath.createText();
		String capStr = capText.substring(0, capText.lastIndexOf("@"));
		String code = capText.substring(capText.lastIndexOf("@") + 1);
		System.out.println("验证码为@@" + capText);
		// 生成图片验证码
		BufferedImage bi = producerMath.createImage(capStr);
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, code);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "/error/{code}")
	public String index(@PathVariable String code, HttpServletRequest request) {
		return "error/" + code;
	}

	/**
	 * 退出系统
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.getSession().removeAttribute("sessionFlag");
		ShiroUtils.logout();
		return "redirect:login";
	}
}
