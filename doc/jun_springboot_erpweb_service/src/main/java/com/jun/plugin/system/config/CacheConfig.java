package com.jun.plugin.system.config;

//package com.jun.plugin.system.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//
//import javax.annotation.PostConstruct;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 
// * @title 该配置文件大部分使用SpringBoot默认配置,仅加入了有期限缓存的键
// */
//@Configuration
//public class CacheConfig {
//
//      @Autowired
//      ResourceLoader resourceLoader;
//
//      @Bean
//      public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//            return RedisCacheManager.builder(redisConnectionFactory)
//                    .cacheDefaults(getDefaultCacheConfiguration())//默认的缓存配置(没有配置键的key均使用此配置)
//                    .withInitialCacheConfigurations(getCacheConfigurations())
//                    .transactionAware() //在spring事务正常提交时才缓存数据
//                    .build();
//      }
//
//      private Map<String, RedisCacheConfiguration> getCacheConfigurations() {
//            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
//
//            //缓存键,且30秒后过期,30秒后再次调用方法时需要重新缓存
//            configurationMap.put("expireKey", this.getDefaultCacheConfiguration(Duration.ofSeconds(30)));
//
//            return configurationMap;
//      }
//
//
//      /**
//       * 获取redis的缓存配置(针对于键)
//       *
//       * @param ttl 键过期时间
//       * @return
//       */
//      private RedisCacheConfiguration getDefaultCacheConfiguration(Duration ttl) {
//            // 获取Redis缓存配置,此处获取的为默认配置
//            final RedisCacheConfiguration defaultCacheConfiguration = getDefaultCacheConfiguration();
//            // 设置键过期的时间,用 java.time 下的Duration表示持续时间,进入entryTtl()方法的源码中可看到
//            // 当设置为 0 即 Duration.ZERO 时表示键无过期时间,其也是默认配置
//            return defaultCacheConfiguration.entryTtl(ttl);
//      }
//
//      /**
//       * 获取Redis缓存配置,此处获取的为默认配置
//       * 如对键值序列化方式,是否缓存null值,是否使用前缀等有特殊要求
//       * 可另行调用 RedisCacheConfiguration 的构造方法
//       *
//       * @return
//       */
//      private RedisCacheConfiguration getDefaultCacheConfiguration() {
//            // 注意此构造函数为 spring-data-redis-2.1.9 及以上拥有,经试验 已知spring-data-redis-2.0.9及以下版本没有此构造函数
//            // 但观察源码可得核心不过是在值序列化器(valueSerializationPair)的构造中注入 ClassLoader 即可
//            return RedisCacheConfiguration.defaultCacheConfig(resourceLoader.getClassLoader());
//      }
//
//}
