package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class OrderLog  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * orderId       db_column: order_id
     */

private Integer orderId;

    /**
     * type       db_column: type
     */

private Integer type;

    /**
     * time       db_column: time
     */

private Date time;

    /**
     * adminId       db_column: admin_id
     */

private Integer adminId;

    /**
     * note       db_column: note
     */

private String note;







}






