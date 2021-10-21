package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 *
 */

@Data
public class Logistic {


    /**
     * id       db_column: id
     */

    @Pk
    private Long id;

    /**
     * 公司名称       db_column: name
     */

    private String name;

    /**
     * 快递公司代码       db_column: code
     */

    private String code;

    /**
     * 公司网址       db_column: company_url
     */

    private String companyUrl;

    /**
     * 排序       db_column: weight
     */

    private Double weight;
    private Date createTime;


}






