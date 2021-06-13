package com.qingshop.mall.framework.shiro.web;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import com.qingshop.mall.common.bean.Rest;
import com.qingshop.mall.common.utils.JsonUtils;
import com.qingshop.mall.common.utils.WebUtils;

/**
 * 用户权限过滤器
 */
public class UserPermFilter extends PermissionsAuthorizationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (WebUtils.isAjax(httpServletRequest)) {
			// ajax请求返回json
			Rest error = Rest.failure("没有权限，请联系管理员授权");
			WebUtils.write(httpServletResponse, JsonUtils.beanToJson(error));
		} else {
			// 非异步请求直接跳转权限错误页面
			httpServletResponse.sendRedirect("/error/illegalAccess");
		}
		return false;
	}
}
