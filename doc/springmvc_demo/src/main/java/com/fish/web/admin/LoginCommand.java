package com.fish.web.admin;

import com.fish.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/20
 * Time: 9:59
 */
public class LoginCommand extends BaseDomain {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
