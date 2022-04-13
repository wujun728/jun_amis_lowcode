package com.element;

import com.element.common.utils.RedisUtils;
import com.element.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        SysUserEntity user = new SysUserEntity();
        user.setEmail("aaaaa@qq.com");
        redisUtils.set("user", user);
        System.out.println(ToStringBuilder.reflectionToString(redisUtils.get("user", SysUserEntity.class)));
    }

}
