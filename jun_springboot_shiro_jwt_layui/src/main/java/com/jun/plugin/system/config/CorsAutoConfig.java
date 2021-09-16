package com.jun.plugin.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * ClassName: CorsAutoConfig Description: layui date: 2020/4/14 18:30
 *
 * 
 * 
 * @date 前后端 解决跨域问题
 * @since JDK 1.8
 */
@Configuration
public class CorsAutoConfig {
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		// 表示什么域名跨域 *表示全部都跨域
		corsConfiguration.addAllowedOrigin("*");
		// 注入进去
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

		CorsFilter corsFilter = new CorsFilter(urlBasedCorsConfigurationSource);
		return corsFilter;
	}
}