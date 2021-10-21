package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class AdItem  {




    /**
     * id       db_column: id
     */

    @Pk
    private Long id;

    /**
     * adSpaceId       db_column: ad_space_id
     */

    private Long adSpaceId;


    /**
     * 广告名称       db_column: name
     */

    private String name;

    /**
     * objectid       db_column: objectid
     */

    private Long objectId;

    /**
     * 状态       db_column: status
     */

private Integer status;

    /**
     * 内容       db_column: content
     */

private String content;

    /**
     * 权重       db_column: weight
     */

private Double weight;

    /**
     * createAdmin       db_column: create_admin
     */

    private String createAdmin;

    /**
     * createTime       db_column: create_time
     */

    private Date createTime;

    /**
     * updateTime       db_column: update_time
     */

    private Date updateTime;


}






