package com.reading.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reading.app.mapper.LoginMapper;
import com.reading.app.mapper.UserMapper;
import com.reading.app.domain.Login;
import com.reading.app.domain.LoginExample;
import com.reading.app.domain.User;
import com.reading.app.service.IEmailService;
import com.reading.app.service.ILoginService;
import com.reading.app.utils.VerifyCodeUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    LoginMapper loginMapper;

    @Autowired
    IEmailService emailService;

    @Autowired
    UserMapper userMapper;

    @Override
    public int register(Login login) {
        LoginExample loginExample = new LoginExample();
        LoginExample.Criteria criteria = loginExample.createCriteria();
        criteria.andAccountEqualTo(login.getAccount());
        List<Login> logins = loginMapper.selectByExample(loginExample);
        if (logins.size()>0){
            return 0;
        }else {
            //增加到登录表中
            int aid = loginMapper.insert(login);
            //增加到用户表中
            User user = new User();
            user.setAid(aid);
            user.setEmail(login.getAccount());
            int ret = userMapper.insertUser(user);
        }
        return 1;
    }

    @Override
    public int login(Login login) {
        LoginExample loginExample = new LoginExample();
        LoginExample.Criteria criteria = loginExample.createCriteria();
        criteria.andAccountEqualTo(login.getAccount());
        criteria.andPasswordEqualTo(login.getPassword());
        List<Login> logins = loginMapper.selectByExample(loginExample);
        if (logins.size() > 0){
            return logins.get(0).getId();
        }
        return 0;
    }

    @Override
    public String verifyCode(Login login) {
        String code = "null";
        if (login.getAccount() != null) {
            code = VerifyCodeUtils.generateVerifyCode(6, login.getAccount());
            String content = "客户： " + login.getAccount() + " 您好，\n" + "\t此次的验证码是: " + code;
            emailService.sendSimpleMail(login.getAccount(), "ReadingApp验证码", content);
        }
        return code;
    }

    @Override
    public int changePwd(Login login) {
        LoginExample loginExample = new LoginExample();
        LoginExample.Criteria criteria = loginExample.createCriteria();
        criteria.andAccountEqualTo(login.getAccount());
        List<Login> logins = loginMapper.selectByExample(loginExample);
        if (logins.size()>0){
            return loginMapper.updateByPrimaryKey(login);
        }
        return 0;
    }
}
