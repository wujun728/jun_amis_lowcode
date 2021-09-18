package com.jun.biz.manager.model;


import com.jun.biz.manager.model.annotation.Pk;

import lombok.Data;

/**
 * 
 */

@Data
public class ConsigneeInfo  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * name       db_column: name
     */

private String name;

    /**
     * cellphone       db_column: cellphone
     */

private String cellphone;

    /**
     * 电话       db_column: tel
     */

private String tel;

    /**
     * 地区       db_column: region
     */

private String region;

    /**
     * address       db_column: address
     */

private String address;

    /**
     * postcode       db_column: postcode
     */

private String postcode;

    /**
     * userId       db_column: user_id
     */

private Integer userId;

    /**
     * 是否为默认收货地址       db_column: default_use
     */

private Boolean defaultUse;







}






