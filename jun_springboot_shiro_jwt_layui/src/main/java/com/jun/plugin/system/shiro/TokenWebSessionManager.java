package com.jun.plugin.system.shiro;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.UUID;

/**
 * ClassName: TokenWebSessionManager Description: layui date: 2020/4/14 20:55
 *
 * 
 * 
 * @since JDK 1.8 解决 session 共享问题 让前端拿到该token 严格是说 shiro+redis 的session共享问题
 */
@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {

	private static final String TOKEN_HEADER = "TOKEN";

	/**
	 * 重写getSessionId方法 这里主要是进行判断 请求当中有没有Token 没有则立马生成一个给到出去 有则在请求头当中拿
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		System.out.println("获取当前请求地址:" + httpServletRequest.getRequestURI());
		// 这里与前端项目结合 吧token存储到 请求头里面
		// 从头里面得到请求TOKEN 如果不存在就生成一个
		String header = WebUtils.toHttp(request).getHeader(TOKEN_HEADER);
		System.out.println("获取TOKEN:" + header);
		if (StringUtils.hasText(header)) {
			return header;
		}
		return UUID.randomUUID().toString();
	}
}