package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class Admin  {




    /**
     * 主键       db_column: id
     */

    @Pk
	private Long id;

    /**
     * 用户名       db_column: username
     */

private String username;

    /**
     * 密码       db_column: password
     */
    private String password;

    /**
     * 真实姓名       db_column: real_name
     */

    private String realName;
    private String email;
    private String phone;

    /**
     * 创建时间       db_column: create_time
     */

    private Date createTime;

    /**
     * 最后登录时间       db_column: last_login_time
     */

private Date lastLoginTime;

    /**
     * 创建人       db_column: create_admin
     */

    private String createAdmin;

    /**
     * 状态，1：正常，2：冻结       db_column: status
     */

private Integer status;







}






