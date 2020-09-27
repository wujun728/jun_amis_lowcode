package com.puboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puboot.entity.User;
import com.puboot.mapper.UserMapper;
import com.puboot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: linzhaoguan
 * Date: 2019-08-30
 * Time: 4:39 下午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
