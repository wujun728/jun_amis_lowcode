package com.jun.plugin.modules.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jun.plugin.common.enums.ResponseStatus;
import com.jun.plugin.common.exception.HtException;

/**
 * 全局定制错误页面
 *
 */
@ControllerAdvice
public class ExceptionHandleController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandleController.class);

	@ExceptionHandler(HtException.class)
	public String handleHt(Exception e, HttpServletRequest request) {
		request.setAttribute("javax.servlet.error.status_code", ResponseStatus.ERROR.getCode());
		Map<String, Object> map = new HashMap<>(2);
		map.put("status", ResponseStatus.ERROR.getCode());
		map.put("msg", StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : ResponseStatus.ERROR.getMessage());
		logger.error(e.getMessage());
		request.setAttribute("extError", map);
		return "forward:/error";
	}

	@ExceptionHandler(AuthorizationException.class)
	public String handleAuth(HttpServletRequest request) {
		request.setAttribute("javax.servlet.error.status_code", ResponseStatus.FORBIDDEN.getCode());
		return "forward:/error";
	}
}
