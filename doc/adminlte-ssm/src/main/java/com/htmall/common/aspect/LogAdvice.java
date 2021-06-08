package com.htmall.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.htmall.common.anno.Log;
import com.htmall.common.util.ShiroUtil;
import com.htmall.common.util.SpringUtil;
import com.htmall.entity.SysLog;
import com.htmall.entity.SysUser;
import com.htmall.service.ISysLogService;

/**
 * 正常业务日志记录
 */
@Aspect
@Component
public class LogAdvice {

	public static final Logger LOG = Logger.getLogger(LogAdvice.class);

	@Pointcut("@annotation(com.htmall.common.anno.Log)")
	public void controllerAspect() {

	}

	/**
	 * 当方法正常返回是执行
	 * 
	 * @param joinPoint
	 */
	@AfterReturning("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Log log = method.getAnnotation(Log.class);
		SysUser sysUser = ShiroUtil.getSessionUser();
		if (log != null) {
			SysLog sysLog = new SysLog();
			sysLog.setCreateTime(new Date());
			sysLog.setTitle(log.value());
			sysLog.setUserName((sysUser != null) ? sysUser.getUserName() : "system");
			sysLog.setUrl(request.getRequestURI().toString());
			sysLog.setParams(JSON.toJSONString(request.getParameterMap()));
			SpringUtil.getBean(ISysLogService.class).save(sysLog);
			LOG.debug("记录日志:" + sysLog.toString());
		}
	}
}
