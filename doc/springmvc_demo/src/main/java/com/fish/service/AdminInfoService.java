package com.fish.service;

import com.fish.dao.AdminDao;
import com.fish.domain.Admin;
import com.fish.web.admin.AdminCommand;
import com.fish.web.admin.UpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/22
 * Time: 12:06
 */
@Service
public class AdminInfoService {
    @Autowired
    private AdminDao adminDao;

    public Admin findAdminByEmail(String email) {
        return adminDao.findByEmail(email);
    }

    public boolean updatePass(String newPass, String email) {
        return adminDao.updatePass(newPass, email);
    }

    public boolean update(UpdateCommand updateCommand,int id) {
        return adminDao.update(updateCommand,id);
    }

    public boolean add(AdminCommand adminCommand) {
        return adminDao.add(adminCommand);
    }
}
