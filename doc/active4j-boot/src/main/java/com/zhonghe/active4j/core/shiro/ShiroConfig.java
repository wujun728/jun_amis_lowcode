package com.zhonghe.active4j.core.shiro;

import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhonghe.active4j.core.redis.RedisPropertiesLoader;
import com.zhonghe.active4j.core.session.MySessionFactory;
import com.zhonghe.active4j.core.session.MyWebSessionManager;

/**
 * 权限管理 shiro核心配置类
 * 
 * @author teli_
 *
 */
@Configuration
public class ShiroConfig {
	
	@Autowired(required=false)
	private RedisPropertiesLoader redisProperties;
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setSessionManager(sessionManager());
		// 设置realm.
		securityManager.setRealm(myShiroRealm());
		if(null != redisProperties) {
			securityManager.setCacheManager(cacheManager());
		}
		return securityManager;
	}

	/**
	 * 
	 * @author 麻木神
	 * @time 2019年12月6日 上午9:20:06
	 */
	public MySessionFactory sessionFactory() {
		MySessionFactory sessionFactory = new MySessionFactory();
		return sessionFactory;
	}

	@Bean
	public MyWebSessionManager sessionManager() {
		/**
		 * 重写了WebSessionManager 重新写了session的验证 集成redissession管理
		 */
		MyWebSessionManager sessionManager = new MyWebSessionManager();
		//修改为10分钟验证一次，主要是为了刷新在线session最后更新时间
		sessionManager.setSessionValidationInterval(10 * 60 * 1000l);
		if(null != redisProperties) {
			sessionManager.setSessionDAO(redisSessionDAO());
		}
		// 自定义sessionfactory 为了在创建session时赋值上浏览器，操作系统等信息
		sessionManager.setSessionFactory(sessionFactory());
		return sessionManager;
	}

	@Bean
	ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/logout", "anon");
		filterChainDefinitionMap.put("/layuiadmin/**", "anon");
		filterChainDefinitionMap.put("/assets/**", "anon");
		filterChainDefinitionMap.put("/bootstrap/**", "anon");
		filterChainDefinitionMap.put("/jquery/**", "anon");
		filterChainDefinitionMap.put("/tools/**", "anon");
		filterChainDefinitionMap.put("/treeview/**", "anon");
		filterChainDefinitionMap.put("/third/**", "anon");
		filterChainDefinitionMap.put("/vercode", "anon");
		filterChainDefinitionMap.put("/func/weixin/**", "anon");
		filterChainDefinitionMap.put("/func/ali/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));

		return hashedCredentialsMatcher;

	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	
	/**
	 * 配置shiro redisManager，使用的是shiro-redis开源插件
	 */
	@Bean
	@ConditionalOnBean(RedisPropertiesLoader.class)
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(redisProperties.getHost() + ":" + redisProperties.getPort());
		if (StringUtils.isNotEmpty(redisProperties.getPassword())) {
			redisManager.setPassword(redisProperties.getPassword());
		}
		redisManager.setTimeout(redisProperties.getTimeout());
		redisManager.setDatabase(redisProperties.getDatabase());
		return redisManager;
	}
	

	@Bean
	@ConditionalOnBean(RedisManager.class)
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		redisSessionDAO.setKeyPrefix("shiro:user:");
		return redisSessionDAO;
	}

	@Bean
	@ConditionalOnBean(RedisManager.class)
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

}
