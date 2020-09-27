package com.itstyle.jwt.service.impl;

import com.itstyle.jwt.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Override
    public List<String> getRoleSignByUserId(Integer userId) {
        List<String> roles = Arrays.asList(new String[]{"admin","user"});
        return roles;
    }
}
