package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class GatewayOrder  {




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
     * gatewayTradeNo       db_column: gateway_trade_no
     */

private String gatewayTradeNo;

    /**
     * createTime       db_column: create_time
     */

private Date createTime;

    /**
     * notifyTime       db_column: notify_time
     */

private Date notifyTime;

    /**
     * status       db_column: status
     */

private Integer status;

    /**
     * amount       db_column: amount
     */

private Double amount;

    /**
     * payType       db_column: pay_type
     */

private Integer payType;







}






