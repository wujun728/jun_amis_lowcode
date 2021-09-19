package com.jun.plugin.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.util.PropertiesUtil;

/**
 * WebMvcConfig配置类-资源映射
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final String FILE_PROTOCOL = "file:///";

	/**
	 * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:index");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String workDir = PropertiesUtil.getString(CoreConst.WORK_DIR_KEY);
		String localPath = workDir.endsWith(File.separator) ? workDir : workDir + File.separator;
		/** 本地文件上传路径 */
		registry.addResourceHandler("/file/**").addResourceLocations(FILE_PROTOCOL + localPath + CoreConst.FILE_ + File.separator);
	}

}
