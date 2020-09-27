package com.itstyle.jwt.service;

import java.util.List;

public interface SysUserService {

    List<String> getRoleSignByUserId(Integer userId);
}
