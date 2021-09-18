package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class SessionToken  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * sessionToken       db_column: session_token
     */

private String sessionToken;

    /**
     * userId       db_column: user_id
     */

    private Long userId;
    private String username;

    /**
     * remoteAddress       db_column: remote_address
     */

private String remoteAddress;

    /**
     * createTime       db_column: create_time
     */

private Date createTime;
private Date loginDate;
private Date updateTime;
private Integer status;







}






