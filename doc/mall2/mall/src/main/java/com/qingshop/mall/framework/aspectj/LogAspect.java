package com.qingshop.mall.framework.aspectj;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.common.utils.spring.SpringUtils;
import com.qingshop.mall.framework.annotation.Log;
import com.qingshop.mall.framework.shiro.ShiroUtils;
import com.qingshop.mall.modules.system.entity.SysLog;
import com.qingshop.mall.modules.system.entity.SysUser;
import com.qingshop.mall.modules.system.service.ISysLogService;

/**
 * 正常业务日志记录
 *
 */
@Aspect
@Component
public class LogAspect {

	@Pointcut("@annotation(com.qingshop.mall.framework.annotation.Log)")
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
		SysUser sysUser = ShiroUtils.getSysUser();
		if (log != null) {
			SysLog sysLog = new SysLog();
			sysLog.setLogId(DistributedIdWorker.nextId());
			sysLog.setTitle(log.value());
			sysLog.setUserName((sysUser != null) ? sysUser.getUserName() : "system");
			sysLog.setUrl(request.getRequestURI().toString());
			sysLog.setParams(JSON.toJSONString(request.getParameterMap()));
			SpringUtils.getBean(ISysLogService.class).save(sysLog);
		}
	}
}
