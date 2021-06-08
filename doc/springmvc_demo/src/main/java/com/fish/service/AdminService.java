package com.fish.service;

import com.fish.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/20
 * Time: 10:16
 */
@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public boolean exists(String email) {
        return adminDao.exists(email);
    }

    public boolean valid(String email, String password) {
        return adminDao.valid(email, password);
    }
}
