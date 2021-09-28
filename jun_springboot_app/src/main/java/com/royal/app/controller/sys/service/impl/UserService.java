package com.royal.app.controller.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.royal.app.controller.sys.entity.AppUser;
import com.royal.app.controller.sys.mapper.UserMapper;

@Service("userService")
public class UserService
{
    private final static Map<String, AppUser> usermap = new HashMap<String, AppUser>();
    {
        AppUser admin = new AppUser(1l, "admin", "123456", "admin", "view,edit,test:test");
        AppUser guest = new AppUser(2l, "guest", "123456", "guest", "view");
        usermap.put("admin", admin);
        usermap.put("guest", guest);
        usermap.put("1", admin);
        usermap.put("2", guest);
    }

    @Autowired
    UserMapper userMapper;

    public AppUser findByUsername(String Loginname)
    {
        // AppUser appUser = new AppUser();
        // appUser.setUsername(username);
        // return userMapper.selectOne(user);
        // 这里演示就直接返回了
        return usermap.get(Loginname);
    }

    public AppUser findUserById(long userId)
    {
        // return userMapper.selectByPrimaryKey(userId);
        // 这里演示就直接返回了
        return usermap.get(userId+"");
    }
}