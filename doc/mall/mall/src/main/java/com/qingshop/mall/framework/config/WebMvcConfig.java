package com.qingshop.mall.framework.config;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.qingshop.mall.common.constant.Constants;
import com.qingshop.mall.common.utils.PropertiesUtil;
import com.qingshop.mall.framework.resolver.LoginUserHandlerMethodArgumentResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer, ErrorPageRegistrar {

	private static final String FILE_PROTOCOL = "file:///";

	@Autowired
	private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

	/**
	 * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:index");
	}

	/**
	 * 映射地址
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String workDir = PropertiesUtil.getString(Constants.WORK_DIR_KEY);
		String localPath = workDir.endsWith(File.separator) ? workDir : workDir + File.separator;
		/** 本地文件上传路径 */
		registry.addResourceHandler("/file/**").addResourceLocations(FILE_PROTOCOL + localPath + Constants.FILE_ + File.separator);

		/** swagger配置 */
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
	}

	/**
	 * 错误页面
	 * 
	 * @param registry
	 */
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage notFound = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
		ErrorPage sysError = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
		registry.addErrorPages(notFound, sysError);
	}

}
