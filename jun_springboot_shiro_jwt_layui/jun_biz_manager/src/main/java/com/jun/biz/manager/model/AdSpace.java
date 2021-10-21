package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 *
 */

@Data
public class AdSpace {


    /**
     * id       db_column: id
     */

    @Pk
    private Long id;

    /**
     * 广告名称       db_column: name
     */

    private String name;

    /**
     * 广告编号       db_column: no
     */

    private String no;

    /**
     * 模板名称       db_column: template_name
     */

    private String template;

    /**
     * 容量       db_column: number
     */

    private Integer number;

    /**
     * 类型       db_column: type
     */

    private Integer type;

    /**
     * 图片       db_column: width
     */

    private Integer width;

    /**
     * height       db_column: height
     */

    private Integer height;
    private Date createTime;


}






