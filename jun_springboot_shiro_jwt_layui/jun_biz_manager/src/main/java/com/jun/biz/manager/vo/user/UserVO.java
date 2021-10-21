package com.jun.biz.manager.vo.user;


import lombok.Data;

import java.util.Date;


/**
 *
 */

@Data
public class UserVO {


    /**
     * id       db_column: id
     */
    private Long id;
    /**
     * 用户名       db_column: username
     */
    private String username;
    /**
     * 邮箱       db_column: email
     */
    private String email;

    private Integer status;
    private Integer type;

    /**
     * 最后登录时间       db_column: last_login_time
     */
    private Date lastLoginTime;
    /**
     * 注册时间       db_column: register_time
     */
    private Date registerTime;


}






