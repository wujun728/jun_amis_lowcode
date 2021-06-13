package com.qingshop.mall.framework.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qingshop.mall.framework.shiro.ShiroTag;

import freemarker.template.Configuration;

/**
 * freemarker与shiro标签
 */
@Component
public class FreemarkerConfig implements InitializingBean {

	@Autowired
	private Configuration configuration;

	@Override
	public void afterPropertiesSet() throws Exception {
		configuration.setSharedVariable("shiro", new ShiroTag());
	}

}
