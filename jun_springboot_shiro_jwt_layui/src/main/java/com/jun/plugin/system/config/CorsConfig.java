package com.jun.plugin.system.config;// package com.sxt.system.config;

//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @program: 0812erp  解决跨域的问题  需要搭配 跨域注解  太麻烦
// * @author: 雷哥
// * @create: 2020-01-04 10:29
// **/
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
////    @Bean
////    public FilterRegistrationBean corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        // 设置你要允许的网站域名，*表示任意域名
//////        config.addAllowedOrigin("http://127.0.0.1");
////        config.addAllowedOrigin("*");
////        //
////        config.addAllowedHeader("*");//* 表示任意头部信息
////        config.addAllowedMethod("GET,POST,PUT,DELETE,HEAD,OPTIONS");
////        source.registerCorsConfiguration("/**", config);
////        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
////        // 这个顺序很重要，为避免麻烦请设置在最前
////        bean.setOrder(0);
////        return bean;
////    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/*")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
//                .allowedHeaders("*")
//                .maxAge(3600);
//    }
//}
