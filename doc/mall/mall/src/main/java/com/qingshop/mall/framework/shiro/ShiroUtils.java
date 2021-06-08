package com.qingshop.mall.framework.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.qingshop.mall.modules.system.entity.SysUser;

/**
 * Shiro工具类
 */
public class ShiroUtils {

	/** 加密算法 */
	public final static String hashAlgorithmName = "SHA-256";

	/** 循环次数 */
	public final static int hashIterations = 16;

	public static String md51024Pwd(String password, Object salt) {
		return new SimpleHash("MD5", password, salt, 1024).toString();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static SysUser getSysUser() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getSysUser().getUserId();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
		if (kaptcha == null) {
			return null;
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}

}
