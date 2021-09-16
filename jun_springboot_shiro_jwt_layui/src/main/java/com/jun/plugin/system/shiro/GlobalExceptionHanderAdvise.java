package com.jun.plugin.system.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: GlobalExceptionHanderAdvise create: 2020-04-28 12:14
 *
 * @author: Wujun @since： JDK1.8
 **/
// 监视Controller 里面是否有异常发生  如果有发生异常  则进行 跳转页面
@RestControllerAdvice
public class GlobalExceptionHanderAdvise {
	/**
	 * 监听捕获未授权异常 UnauthorizedException 未授权
	 */
	@ExceptionHandler(value = { UnauthorizedException.class })
	public Object unauthorized() {
		Map<String, Object> map = new HashMap<>();
		map.put("code", -1);
		map.put("msg", "未授权，请联系管理员");
		return map;
	}

}