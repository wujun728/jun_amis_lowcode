package com.element.modules.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.element.common.exception.CustomException;
import com.element.common.validator.Assert;
import com.element.modules.app.entity.UserEntity;
import com.element.modules.app.form.LoginForm;
import com.element.modules.app.mapper.UserMapper;
import com.element.modules.app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserEntity getUserByMobile(String mobile) {
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
    }

    @Override
    public long login(LoginForm form) {
        UserEntity user = getUserByMobile(form.getMobile());
        Assert.isNull(user, "手机号或密码错误");
        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))) {
            throw new CustomException("手机号或密码错误");
        }
        return user.getUserId();
    }
}
