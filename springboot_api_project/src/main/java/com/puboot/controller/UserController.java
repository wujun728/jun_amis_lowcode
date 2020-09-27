package com.puboot.controller;

import com.puboot.common.utils.R;
import com.puboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: linzhaoguan
 * Date: 2019-08-30
 * Time: 4:54 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public R list() {
        return R.ok(userService.list());
    }
}
