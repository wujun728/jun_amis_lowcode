package com.htmall.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.htmall.entity.SysUser;

/**
 * Shiro工具类
 */
public class ShiroUtil {

	/**
	 * 密码加密
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String md51024Pwd(String password,Object salt){
		return new SimpleHash("MD5", password, salt, 1024).toString();
	}
	
	/**
	 * 获取当前Session中的用户
	 * @return
	 */
	public static SysUser getSessionUser(){
		
		Subject subject = SecurityUtils.getSubject();
		if(subject != null){
			Object object = subject.getPrincipal();
			if(object != null){
				SysUser sysUser = (SysUser) object;
				return sysUser;
			}
		}
		return null;
	}
	
	/**
	 * 获取当前用户ID
	 * @return
	 */
	public static String getSessionUid(){
		
		SysUser sysUser = getSessionUser();
		
		if(sysUser != null){
			
			return sysUser.getId();
		}
		
		return null;
	}
	
	/**
	 * 获取当前session
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	/**
	 * 往当前session赋值
	 * @return
	 */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 从当前session取值
	 * @return
	 */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}
	
	/**
	 * 从当前session移除值
	 * @return
	 */
	public static Object removeSessionAttribute(Object key) {
		return getSession().removeAttribute(key);
	}
}
