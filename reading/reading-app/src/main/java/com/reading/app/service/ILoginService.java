package com.reading.app.service;

import com.reading.app.domain.Login;

public interface ILoginService {

    int register(Login login) ;

    int login(Login login) ;

    String verifyCode(Login login) ;

    int changePwd(Login login);

}
