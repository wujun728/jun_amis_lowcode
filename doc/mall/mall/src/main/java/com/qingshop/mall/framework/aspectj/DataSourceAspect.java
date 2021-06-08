package com.qingshop.mall.framework.aspectj;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.framework.annotation.DataSource;
import com.qingshop.mall.framework.datasource.DynamicDataSourceContextHolder;

/**
 * 多数据源处理
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("@annotation(com.qingshop.mall.framework.annotation.DataSource)")
	public void dsPointCut() {

	}

	@Around("dsPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature) point.getSignature();

		Method method = signature.getMethod();

		DataSource dataSource = method.getAnnotation(DataSource.class);

		if (StringUtils.isNotNull(dataSource)) {
			DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
		}

		try {
			return point.proceed();
		} finally {
			// 销毁数据源 在执行方法之后
			DynamicDataSourceContextHolder.clearDataSourceType();
		}
	}
}
