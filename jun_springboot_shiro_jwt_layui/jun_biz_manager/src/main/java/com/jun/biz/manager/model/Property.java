package com.jun.biz.manager.model;


import com.jun.biz.manager.model.annotation.Pk;

import lombok.Data;

/**
 * 
 */

@Data
public class Property  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * categoryid       db_column: categoryid
     */

private Integer categoryid;

    /**
     * 属性名       db_column: name
     */

private String name;

    /**
     * propertyValues       db_column: property_values
     */

private String propertyValues;







}






