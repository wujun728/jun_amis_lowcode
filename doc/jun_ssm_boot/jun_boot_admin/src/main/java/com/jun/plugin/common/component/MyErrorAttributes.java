package com.jun.plugin.common.component;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * 让后端抛出的异常以固定的状态格式返回，使用了DefaultErrorAttributes
 *
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		@SuppressWarnings("deprecation")
		Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
		@SuppressWarnings("unchecked")
		Map<String, Object> extErrorMessage = (Map<String, Object>) webRequest.getAttribute("ext", 0);
		map.put("extError", extErrorMessage);
		return map;
	}
}
