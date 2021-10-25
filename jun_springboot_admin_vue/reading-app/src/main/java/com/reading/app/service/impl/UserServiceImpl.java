package com.reading.app.service.impl;


import com.reading.app.mapper.UserMapper;
import com.reading.app.domain.User;
import com.reading.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectUserByAid(int aid) {
        return userMapper.selectUserzbyAid(aid);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
