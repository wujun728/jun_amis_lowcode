package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 *
 */

@Data
public class Role {


    /**
     * id       db_column: id
     */

    @Pk
    private Long id;

    /**
     * 名称       db_column: name
     */

    private String name;

    /**
     * 描述       db_column: description
     */

    private String description;


    /**
     * 创建时间       db_column: create_time
     */

    private Date createTime;

    private String createAdmin;


}






