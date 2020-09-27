package com.puboot.service;

import com.puboot.BaseTester;
import com.puboot.entity.User;
import com.puboot.entity.enums.SexEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linzhaoguan
 * Date: 2019-08-30
 * Time: 4:40 下午
 */
@Slf4j
public class UserServiceTest extends BaseTester {

    @Autowired
    private UserService userService;

    @Test
    public void testList() {
        List<User> list = userService.list();
        log.info("result: {}", list);
    }

}
