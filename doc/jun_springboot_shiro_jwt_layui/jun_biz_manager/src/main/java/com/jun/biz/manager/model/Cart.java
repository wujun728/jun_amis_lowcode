package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class Cart  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * userId       db_column: user_id
     */

private Integer userId;

    /**
     * createTime       db_column: create_time
     */

private Date createTime;

    /**
     * content       db_column: content
     */

private String content;







}






