package com.jun.biz.controller;

import java.io.Serializable;
import java.util.Date;

/**
*  用例信息
*
*  Created by wujun on '2021-03-24 01:01:30'.
*/
public class TTestcase implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
    * 用户ID
    */
    private int userId;

    /**
    * 用户名
    */
    private String username;

    /**
    * 创建时间
    */
    private Date addtime;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

}