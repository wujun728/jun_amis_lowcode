package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class Favorite  {




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
     * picUrl       db_column: pic_url
     */

private String picUrl;

    /**
     * price       db_column: price
     */

private Double price;

    /**
     * type       db_column: type
     */

private Integer type;

    /**
     * objectid       db_column: objectid
     */

private Integer objectid;

    /**
     * createTime       db_column: create_time
     */

private Date createTime;

    /**
     * userId       db_column: user_id
     */

private Integer userId;







}






