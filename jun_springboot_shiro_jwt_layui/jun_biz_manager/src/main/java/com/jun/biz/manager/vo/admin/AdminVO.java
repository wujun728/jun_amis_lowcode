package com.jun.biz.manager.vo.admin;


import lombok.Data;

import java.util.Date;
import java.util.List;

import com.jun.biz.manager.model.AdminRole;


/**
 *
 */

@Data
public class AdminVO {


    /**
     * 主键       db_column: id
     */
    private Long id;
    /**
     * 用户名       db_column: username
     */
    private String username;

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
     * 创建人       db_column: create_admin_id
     */
    private String createAdmin;

    /**
     * 状态，1：正常，2：冻结       db_column: status
     */
    private Integer status;

    private List<AdminRole> roles;

}






