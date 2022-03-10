package com.jun.plugin.system.common;

/**
 * ClassName: AppUtils
 * Description: layui
 * date: 2020/4/14 20:12
 *
 * 
 * 
 * @since JDK 1.8
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 当实现了ApplicationContextAware这后， IOC容器初始化时会回调setApplicationContext把IOC容器对象转到里面
 * 解决代理对象 骤停问题
 * 
 */

@Component // 交给ioc管理
public class AppUtils implements ApplicationContextAware {

	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
}
