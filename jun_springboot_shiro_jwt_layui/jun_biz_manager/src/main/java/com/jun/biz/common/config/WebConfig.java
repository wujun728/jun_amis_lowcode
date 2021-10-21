package com.jun.biz.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jun.biz.common.interceptor.AuthorizeInterceptor;
import com.jun.biz.common.interceptor.PermissionInterceptor;

/**
 * 
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public AuthorizeInterceptor authorizeInterceptor() {
        return new AuthorizeInterceptor();
    }

    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizeInterceptor()).addPathPatterns("/**").excludePathPatterns("/error", "/api/auth/*");
        registry.addInterceptor(permissionInterceptor()).addPathPatterns("/**");
    }


}