package com.jun.plugin.system.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jun.plugin.system.common.ActiveUser;
import com.jun.plugin.system.common.WebUtils;
import com.jun.plugin.system.domain.Systemlog;
import com.jun.plugin.system.domain.User;
import com.jun.plugin.system.mapper.SystemlogMapper;
import com.jun.plugin.system.webapi.BaiduMap;
import com.jun.plugin.system.webapi.IP;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * ClassName: SystemAspect Description: layui date: 2020/4/15 18:05
 *
 * 
 * 
 * @since JDK 1.8
 */
//@Aspect
//@Component
//@EnableAspectJAutoProxy
public class SystemAspect {

	@Autowired
	private SystemlogMapper systemlogMapper;
	/**
	 * 日志信息输入
	 */
	private Log log = LogFactory.getLog(SystemAspect.class);
	/* JoinPoint 连接点获取方法 日志执行之前的方法 */
	public static final String POINTCUT_ADD = "execution(* com.jun.plugin.system.service.*.*(..))";

	/**
	 * 定义切入点
	 */
	@Pointcut("execution(public * com.jun.plugin.system.controller.*.*(..))")
	public void webPointCut() {
	}

	@Before("webPointCut()")
	public void writeLog(JoinPoint joinpoint) throws Throwable {
		log.info("开始插入操作日志");
		// 设置时间
		Systemlog systemlog = new Systemlog();
		systemlog.setOptime(new Date());
		// 设置ip地址
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		String addressIp = null;
		if (request != null) {
			// 获取自定义拦截器请求
			String ip = request.getRemoteAddr();
			systemlog.setIp(ip);
			addressIp = ip;
		}
		// 设置当前执行的方法
		// 获取目标执行方法的全路径
		String name = joinpoint.getTarget().getClass().getName();
		// 获取方法名称
		String signature = joinpoint.getSignature().getName();
		String func = name + ":" + signature;
		// 设置当前用户执行了哪些方法
		systemlog.setFunction(func);
		// 获取方法参数
		String params = new ObjectMapper().writeValueAsString(joinpoint.getArgs());
		systemlog.setParams(params);
		// 设置当前用户
		User thisName = (User) WebUtils.getThisName();
		if (null != thisName) {
			systemlog.setThisname(thisName.getName());
		} else {
			systemlog.setThisname("未知用户");
		}

		// 设置ip地址
		BaiduMap baiduMap = new BaiduMap();
		baiduMap.setAddress(addressIp);
		// response 堵塞
//        try {
//            baiduMap = IP.getWebapiAddress(addressIp, "nMBfefpMVsdP6BUVn350G1ByUmOu07GK");
//        } catch (IOException | NoSuchFieldException e) {
//            e.printStackTrace();
//        }
		assert baiduMap != null;
		systemlog.setAddress(baiduMap.getAddress());
		// 注入到数据库
		systemlogMapper.insert(systemlog);
		log.info("结束插入操作日志");
	}
}