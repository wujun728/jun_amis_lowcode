package com.zhonghe.active4j.core.session;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.core.util.IpUtil;
import com.zhonghe.active4j.core.util.RequestUtil;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * @title MySessionFactory.java
 * @description 自定义session factory 赋值浏览器、操作系统等信息
 * @time 2019年12月6日 上午9:20:57
 * @author 麻木神
 * @version 1.0
 */
public class MySessionFactory implements SessionFactory {

	@Override
	public Session createSession(SessionContext initData) {
		SimpleSession session = new SimpleSession();
		
		if (initData != null && initData instanceof WebSessionContext) {
			WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (null != request) {
            	UserAgent userAgent = UserAgent.parseUserAgentString(RequestUtil.getRequest().getHeader("User-Agent"));
            	 // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                session.setHost(IpUtil.getIpAddr(request));
                
                //借用session的attribute赋值
                session.setAttribute(GlobalSessionConstant.SESSION_OS, os);
                session.setAttribute(GlobalSessionConstant.SESSION_BROWSER, browser);
                session.setAttribute(GlobalSessionConstant.SESSION_SAVE_TIME, DateUtils.getNow());
                session.setAttribute(GlobalSessionConstant.SESSION_NEED_SAVE, false);
            }
		}
		
		return session;
	}

}
