package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class Snapshot  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * productId       db_column: product_id
     */

private Integer productId;

    /**
     * content       db_column: content
     */

private String content;

    /**
     * createTime       db_column: create_time
     */

private Date createTime;







}






