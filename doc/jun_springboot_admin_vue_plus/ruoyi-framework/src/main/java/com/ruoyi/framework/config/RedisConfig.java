package com.ruoyi.framework.config;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.properties.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * redis配置
 *
 * @author Lion Li
 */
@Slf4j
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	private static final String REDIS_PROTOCOL_PREFIX = "redis://";
	private static final String REDISS_PROTOCOL_PREFIX = "rediss://";

	@Autowired
	private RedisProperties redisProperties;

	@Autowired
	private RedissonProperties redissonProperties;

	@Bean(destroyMethod = "shutdown")
	@ConditionalOnMissingBean(RedissonClient.class)
	public RedissonClient redisson() throws IOException {
		String prefix = REDIS_PROTOCOL_PREFIX;
		if (redisProperties.isSsl()) {
			prefix = REDISS_PROTOCOL_PREFIX;
		}
		Config config = new Config();
		config.setThreads(redissonProperties.getThreads())
			.setNettyThreads(redissonProperties.getNettyThreads())
			.setCodec(JsonJacksonCodec.INSTANCE)
			.setTransportMode(redissonProperties.getTransportMode());

		RedissonProperties.SingleServerConfig singleServerConfig = redissonProperties.getSingleServerConfig();
		if (ObjectUtil.isNotNull(singleServerConfig)) {
			// 使用单机模式
			config.useSingleServer()
					.setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort())
					.setConnectTimeout(((Long) redisProperties.getTimeout().toMillis()).intValue())
					.setDatabase(redisProperties.getDatabase())
					.setPassword(StringUtils.isNotBlank(redisProperties.getPassword()) ? redisProperties.getPassword() : null)
					.setTimeout(singleServerConfig.getTimeout())
					.setRetryAttempts(singleServerConfig.getRetryAttempts())
					.setRetryInterval(singleServerConfig.getRetryInterval())
					.setSubscriptionsPerConnection(singleServerConfig.getSubscriptionsPerConnection())
					.setClientName(singleServerConfig.getClientName())
					.setIdleConnectionTimeout(singleServerConfig.getIdleConnectionTimeout())
					.setSubscriptionConnectionMinimumIdleSize(singleServerConfig.getSubscriptionConnectionMinimumIdleSize())
					.setSubscriptionConnectionPoolSize(singleServerConfig.getSubscriptionConnectionPoolSize())
					.setConnectionMinimumIdleSize(singleServerConfig.getConnectionMinimumIdleSize())
					.setConnectionPoolSize(singleServerConfig.getConnectionPoolSize())
					.setDnsMonitoringInterval(singleServerConfig.getDnsMonitoringInterval());
		}

		RedissonProperties.ClusterServersConfig clusterServersConfig = redissonProperties.getClusterServersConfig();
		if (ObjectUtil.isNotNull(clusterServersConfig)) {
			// 使用集群模式
			config.useClusterServers()
					.setConnectTimeout(((Long) redisProperties.getTimeout().toMillis()).intValue())
					.setPassword(StringUtils.isNotBlank(redisProperties.getPassword()) ? redisProperties.getPassword() : null)
					.setTimeout(clusterServersConfig.getTimeout())
					.setRetryAttempts(clusterServersConfig.getRetryAttempts())
					.setRetryInterval(clusterServersConfig.getRetryInterval())
					.setSubscriptionsPerConnection(clusterServersConfig.getSubscriptionsPerConnection())
					.setClientName(clusterServersConfig.getClientName())
					.setIdleConnectionTimeout(clusterServersConfig.getIdleConnectionTimeout())
					.setPingConnectionInterval(clusterServersConfig.getPingConnectionInterval())
					.setSubscriptionConnectionMinimumIdleSize(clusterServersConfig.getSubscriptionConnectionMinimumIdleSize())
					.setSubscriptionConnectionPoolSize(clusterServersConfig.getSubscriptionConnectionPoolSize())
					.setMasterConnectionMinimumIdleSize(clusterServersConfig.getMasterConnectionMinimumIdleSize())
					.setMasterConnectionPoolSize(clusterServersConfig.getMasterConnectionPoolSize())
					.setSlaveConnectionMinimumIdleSize(clusterServersConfig.getSlaveConnectionMinimumIdleSize())
					.setSlaveConnectionPoolSize(clusterServersConfig.getSlaveConnectionPoolSize())
					.setDnsMonitoringInterval(clusterServersConfig.getDnsMonitoringInterval())
					.setFailedSlaveReconnectionInterval(clusterServersConfig.getFailedSlaveReconnectionInterval())
					.setScanInterval(clusterServersConfig.getScanInterval())
					.setReadMode(clusterServersConfig.getReadMode())
					.setSubscriptionMode(clusterServersConfig.getSubscriptionMode())
					.setNodeAddresses(redisProperties.getCluster().getNodes());
		}
		RedissonClient redissonClient = Redisson.create(config);
		log.info("初始化 redis 配置");
		return redissonClient;
	}

	/**
	 * 整合spring-cache
	 */
	@Bean
	public CacheManager cacheManager(RedissonClient redissonClient) {
		List<RedissonProperties.CacheGroup> cacheGroup = redissonProperties.getCacheGroup();
		Map<String, CacheConfig> config = new HashMap<>();
		for (RedissonProperties.CacheGroup group : cacheGroup) {
			CacheConfig cacheConfig = new CacheConfig(group.getTtl(), group.getMaxIdleTime());
			cacheConfig.setMaxSize(group.getMaxSize());
			config.put(group.getGroupId(), cacheConfig);
		}
		return new RedissonSpringCacheManager(redissonClient, config, JsonJacksonCodec.INSTANCE);
	}

}
