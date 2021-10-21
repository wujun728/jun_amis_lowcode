package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class ProductLog  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * time       db_column: time
     */

private Date time;

    /**
     * type       db_column: type
     */

private Integer type;

    /**
     * adminId       db_column: admin_id
     */

private Integer adminId;

    /**
     * productId       db_column: product_id
     */

private Integer productId;







}






