package com.fish.web.admin;

import com.fish.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/26
 * Time: 9:50
 */
public class UpdateCommand extends BaseDomain {
    private String username;
    private String website;
    private String desc;
    private String email;

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
}
