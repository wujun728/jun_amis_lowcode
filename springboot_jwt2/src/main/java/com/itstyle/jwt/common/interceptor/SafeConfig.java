package com.itstyle.jwt.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截访问配置
 */
@Configuration
public class SafeConfig implements WebMvcConfigurer {

    @Bean
    public SysInterceptor myInterceptor(){
        return new SysInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE","OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[] { "/user/login","/*.html"};
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}