package com.ruoyi.framework.config.dialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Thymeleaf配置
*/
@Configuration
public class ThymeleafDialectConfig {
    /**
     * 自定义方言注入
     * 字典下拉框下拉框
     * @return
     */
    @Bean
    public RyDialect ryDialect() {
        return new RyDialect();
    }
}