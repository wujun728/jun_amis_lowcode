package com.fish.web.admin;

import com.fish.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/26
 * Time: 10:34
 */
public class AdminCommand extends BaseDomain {
    private String username;
    private String website;
    private String desc;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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
