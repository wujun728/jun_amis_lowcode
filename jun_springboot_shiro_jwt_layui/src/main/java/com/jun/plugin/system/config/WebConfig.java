package com.jun.plugin.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * 
 * ClassName: WebConfig create: 2020-04-30 08:58
 *
 * @author: Wujun @since： JDK1.8
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 解决静态资源无法访问
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		// 解决swagger无法访问
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		// 解决swagger的js文件无法访问
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

//      @Bean
//      public AuthorizeInterceptor authorizeInterceptor() {
//          return new AuthorizeInterceptor();
//      }
//
//      @Bean
//      public PermissionInterceptor permissionInterceptor() {
//          return new PermissionInterceptor();
//      }
//
//      @Override
//      public void addInterceptors(InterceptorRegistry registry) {
//          registry.addInterceptor(authorizeInterceptor()).addPathPatterns("/**").excludePathPatterns("/error", "/api/auth/*");
//          registry.addInterceptor(permissionInterceptor()).addPathPatterns("/**");
//      }

}
