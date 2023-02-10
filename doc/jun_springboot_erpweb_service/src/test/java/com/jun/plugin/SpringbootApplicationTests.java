package com.jun.plugin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.jun.plugin.business.domain.Goods;
import com.jun.plugin.business.domain.Provider;

import java.util.Set;

@SpringBootTest
class SpringbootApplicationTests {

  
    
    @Test
    void contextLoads() {
    }

}
