package com.qingshop.mall.framework.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qingshop.mall.common.utils.StringUtils;
import com.qingshop.mall.framework.shiro.MallRealm;
import com.qingshop.mall.framework.shiro.web.UserFilter;
import com.qingshop.mall.framework.shiro.web.UserPermFilter;

/**
 * 权限配置加载
 */
@Configuration
public class ShiroConfig {

	/**
	 * 设置Session有效时间
	 */
	@Value("${shiro.session.globalSessionTimeout}")
	private int globalSessionTimeout;

	/**
	 * 是否开启redis实现集群部署
	 */
	@Value("${global.redisrun}")
	private boolean redisrun;

	/**
	 * 设置Cookie的域名
	 */
	@Value("${shiro.cookie.domain}")
	private String domain;

	/**
	 * 设置cookie的有效访问路径
	 */
	@Value("${shiro.cookie.path}")
	private String path;

	/**
	 * 设置HttpOnly属性
	 */
	@Value("${shiro.cookie.httpOnly}")
	private boolean httpOnly;

	/**
	 * 设置Cookie的过期时间，秒为单位
	 */
	@Value("${shiro.cookie.maxAge}")
	private int maxAge;

	/**
	 * 设置cipherKey密钥（安全密钥部署请重新生成）
	 */
	@Value("${shiro.cookie.cipherKey}")
	private String cipherKey;

	/**
	 * 登录地址
	 */
	@Value("${shiro.user.loginUrl}")
	private String loginUrl;

	/**
	 * 权限认证失败地址
	 */
	@Value("${shiro.user.unauthorizedUrl}")
	private String unauthorizedUrl;

	/**
	 * redis数据库 存储索引
	 */
	@Value("${spring.redis.database}")
	private int database;

	/**
	 * redis数据库 IP地址
	 */
	@Value("${spring.redis.host}")
	private String host;

	/**
	 * redis数据库 密码
	 */
	@Value("${spring.redis.password}")
	private String password;

	/**
	 * redis数据库 端口
	 */
	@Value("${spring.redis.port}")
	private int port;

	/**
	 * redis数据库 缓存过期时间
	 */
	@Value("${spring.redis.timeout}")
	private int timeout;

	private final int EXPIRE = 1800;

	/**
	 * 缓存管理器 使用Ehcache实现
	 */
	@Bean
	public EhCacheManager getEhCacheManager() {
		net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("mall-ehcache-shiro");
		EhCacheManager em = new EhCacheManager();
		if (StringUtils.isNull(cacheManager)) {
			em.setCacheManager(new net.sf.ehcache.CacheManager(
					getCacheManagerConfigFileInputStream("classpath:ehcache/ehcache-shiro.xml")));
			return em;
		} else {
			em.setCacheManager(cacheManager);
			return em;
		}
	}

	/**
	 * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
	 */
	protected InputStream getCacheManagerConfigFileInputStream(String configFile) {
		InputStream inputStream = null;
		try {
			inputStream = ResourceUtils.getInputStreamForPath(configFile);
			byte[] b = IOUtils.toByteArray(inputStream);
			InputStream in = new ByteArrayInputStream(b);
			return in;
		} catch (IOException e) {
			throw new ConfigurationException(
					"Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	/**
	 * 缓存管理器 使用 RedisCache 实现
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setDatabase(database);
		redisManager.setHost(host + ":" + port);
		redisManager.setTimeout(timeout);
		if (StringUtils.isNotBlank(password)) {
			redisManager.setPassword(password);
		}
		return redisManager;
	}

	public RedisCacheManager getRedisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setPrincipalIdFieldName("userId");
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * 单机环境，session交给shiro管理, 集群环境：session交给redis管理
	 * 
	 * @ConditionalOnProperty 说明:当redisrun设置为false时该段代码生效
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		if (redisrun) {
			DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
			sessionManager.setSessionDAO(redisSessionDAO());
			sessionManager.setSessionIdCookie(sessionIdCookie());
			return sessionManager;
		} else {
			DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
			sessionManager.setSessionIdCookie(sessionIdCookie());
			sessionManager.setSessionValidationSchedulerEnabled(true);
			sessionManager.setSessionIdUrlRewritingEnabled(false);
			sessionManager.setSessionValidationInterval(globalSessionTimeout * 1000);
			sessionManager.setGlobalSessionTimeout(globalSessionTimeout * 1000);
			return sessionManager;
		}
	}

	/**
	 * 设置sessionIdCookie
	 * 
	 * @return
	 */
	private SimpleCookie sessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie();
		cookie.setName("MALLID");
		cookie.setHttpOnly(true);
		return cookie;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		redisSessionDAO.setExpire(EXPIRE);
		return redisSessionDAO;
	}

	/**
	 * 自定义Realm
	 */
	@Bean
	public MallRealm myShiroRealm() {
		MallRealm myShiroRealm = new MallRealm();
		myShiroRealm.setCacheManager(redisrun ? getRedisCacheManager() : getEhCacheManager());
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	/**
	 * 密码加密
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(1024);
		return hashedCredentialsMatcher;
	}

	@Bean("securityManager")
	public SecurityManager securityManager(SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setSessionManager(sessionManager);
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * Shiro过滤器配置
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		// 自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		filtersMap.put("user", new UserFilter());
		filtersMap.put("perms", new UserPermFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

		filterChainDefinitionMap.put("/app/**", "anon");
		filterChainDefinitionMap.put("/common/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/plugins/**", "anon");
		filterChainDefinitionMap.put("/captcha", "anon");
		filterChainDefinitionMap.put("/doLogin", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/file/**", "anon");
		filterChainDefinitionMap.put("/wx/**", "anon");
		filterChainDefinitionMap.put("/**", "user,perms");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/**
	 * cookie 属性设置
	 */
	public SimpleCookie rememberMeCookie() {
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setHttpOnly(httpOnly);
		cookie.setMaxAge(maxAge * 24 * 60 * 60);
		return cookie;
	}

	/**
	 * 记住我
	 */
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		cookieRememberMeManager.setCipherKey(Base64.decode(cipherKey));
		return cookieRememberMeManager;
	}

}
