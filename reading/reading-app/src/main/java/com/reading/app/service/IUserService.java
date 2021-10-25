package com.reading.app.service;

import com.reading.app.domain.User;

public interface IUserService {

    User selectUserByAid(int aid);

    int updateUser(User user);

}
