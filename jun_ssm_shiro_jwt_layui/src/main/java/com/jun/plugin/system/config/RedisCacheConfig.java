package com.jun.plugin.system.config;

//package com.jun.plugin.system.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 
// * 
// * ClassName:  RedisCacheConfig
// * create:  2020-04-27 12:13
// *
// * @author: Wujun
// * @since： JDK1.8
// * redis注解存储对象  进行反序列化  形成json串
// * 在 序列化为对象
// **/
//
////@Configuration
//public class RedisCacheConfig {
//
//      @Bean
//      public KeyGenerator simpleKeyGenerator() {
//            return (o, method, objects) -> {
//                  StringBuilder stringBuilder = new StringBuilder();
//                  stringBuilder.append(o.getClass().getSimpleName());
//                  stringBuilder.append(".");
//                  stringBuilder.append(method.getName());
//                  stringBuilder.append("[");
//                  for (Object obj : objects) {
//                        stringBuilder.append(obj.toString());
//                  }
//                  stringBuilder.append("]");
//
//                  return stringBuilder.toString();
//            };
//      }
//
//      @Bean
//      public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//            return new RedisCacheManager(
//                    RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
//                    // 默认策略，未配置的 key 会使用这个
//                    this.getRedisCacheConfigurationWithTtl(7 * 24 * 3600),
//                    // 指定 key 策略
//                    this.getRedisCacheConfigurationMap()
//            );
//      }
//
//      private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
//            Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//            // 指定key 缓存时间
//            redisCacheConfigurationMap.put("UserInfoList", this.getRedisCacheConfigurationWithTtl(3000));
//            redisCacheConfigurationMap.put("UserInfoListAnother", this.getRedisCacheConfigurationWithTtl(18000));
//
//            return redisCacheConfigurationMap;
//      }
//
//      private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
//            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//            ObjectMapper om = new ObjectMapper();
//            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//            jackson2JsonRedisSerializer.setObjectMapper(om);
//
//            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//            redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
//                    RedisSerializationContext
//                            .SerializationPair
//                            .fromSerializer(jackson2JsonRedisSerializer)
//            ).entryTtl(Duration.ofSeconds(seconds));
//
//            return redisCacheConfiguration;
//      }
//}