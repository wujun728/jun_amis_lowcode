package com.jun.biz.manager.model;


import com.jun.biz.manager.model.annotation.Pk;

import lombok.Data;

/**
 * 
 */

@Data
public class OrderItem  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * 订单       db_column: order_id
     */

private Integer orderId;

    /**
     * 订单金额       db_column: amount
     */

private Double amount;

    /**
     * 单价       db_column: price
     */

private Double price;

    /**
     * 数量       db_column: quantity
     */

private Integer quantity;

    /**
     * 留言       db_column: message
     */

private String message;

    /**
     * 商品快照       db_column: snapshot_id
     */

private Integer snapshotId;

    /**
     * 订单名称       db_column: name
     */

private String name;

    /**
     * 商品图片url       db_column: pic_url
     */

private String picUrl;

    /**
     * 商品ID       db_column: product_id
     */

private Integer productId;







}






