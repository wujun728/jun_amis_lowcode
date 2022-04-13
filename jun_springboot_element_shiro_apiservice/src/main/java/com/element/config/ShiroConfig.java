package com.element.config;

import com.element.modules.sys.oauth2.OAuth2Filter;
import com.element.modules.sys.oauth2.OAuth2Realm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.open}")
    private boolean redisOpen;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String redisPassword;

    /**
     * 缓存管理器 使用 RedisCache 实现
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setTimeout(1800);
        redisManager.setDatabase(database);
        redisManager.setHost(host + ":" + port);
        if (StringUtils.isNotBlank(redisPassword)) {
            redisManager.setPassword(redisPassword);
        }
        return redisManager;
    }

    /**
     * 使用shiro-redis插件实现权限缓存到redis
     */
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        // redis中针对不同用户缓存(此处的id需要对应user实体中的id字段,用于唯一标识)
        redisCacheManager.setPrincipalIdFieldName("userId");
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 认证权限管理
     */
    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        if (redisOpen) {
            securityManager.setCacheManager(redisCacheManager());
        }
        securityManager.setRealm(oAuth2Realm);
        // 关闭shiro默认session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        // 关闭记忆功能
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    /**
     * 权限过滤链
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        // oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);
        // 拦截过滤
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/app/**", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/doc.html", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/**", "oauth2");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        // 返回过滤链
        return shiroFilter;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
