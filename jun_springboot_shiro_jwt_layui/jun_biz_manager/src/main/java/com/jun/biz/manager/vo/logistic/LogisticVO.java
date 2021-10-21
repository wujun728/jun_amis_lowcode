package com.jun.biz.manager.vo.logistic;


import lombok.Data;


/**
 *
 */

@Data
public class LogisticVO {


    /**
     * id       db_column: id
     */
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


}






