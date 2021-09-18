package com.jun.biz.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Slf4j
public class HttpUtil {

	private static final String AJAX_REQUEST_HEADER_NAME = "X-Requested-With";
	private static final String AJAX_REQUEST_HEADER_VALUE = "XMLHttpRequest";
	private static final String AJAX_REQUEST_PREFIX = "ajax-";
	private static final String DEFAULT_CHARSET = "utf-8";


	public static boolean isAjax(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return AJAX_REQUEST_HEADER_VALUE.equals(request.getHeader(AJAX_REQUEST_HEADER_NAME)) || uri.substring(uri.lastIndexOf("/") + 1).startsWith(AJAX_REQUEST_PREFIX);
	}

	public static String getRemoteAddr(HttpServletRequest request) {
		//nginx 代理
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
