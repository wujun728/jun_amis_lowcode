package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class Category  {


    /**
     * id       db_column: id
     */

    @Pk
    private Long id;

    /**
     * 上级id       db_column: pid
     */

    private Long pid;

    /**
     * 分类名称       db_column: name
     */

    private String name;

    /**
     * 描述       db_column: description
     */

private String description;

    /**
     * 排序       db_column: weight
     */

private Double weight;



    /**
     * 创建时间       db_column: create_time
     */

    private Date createTime;

    /**
     * 创建人       db_column: create_admin
     */

    private String createAdmin;

    /**
     * 是否是叶子       db_column: leaf
     */

    private Boolean leaf;


}






