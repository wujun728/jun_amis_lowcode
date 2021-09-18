package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class Order  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * 订单号       db_column: no
     */

private String no;

    /**
     * 订单状态       db_column: status
     */

private Integer status;

    /**
     * 订单金额       db_column: total_amount
     */

private Double totalAmount;

    /**
     * 支付金额       db_column: pay_amount
     */

private Double payAmount;

    /**
     * 退款金额       db_column: refund_amount
     */

private Double refundAmount;

    /**
     * 订单数量       db_column: total_quantity
     */

private Integer totalQuantity;

    /**
     * consumerUserId       db_column: consumer_user_id
     */

private Integer consumerUserId;

    /**
     * 收货人电话       db_column: consignee_tel
     */

private String consigneeTel;

    /**
     * 收货人手机       db_column: consignee_cellphone
     */

private String consigneeCellphone;

    /**
     * 收货人姓名       db_column: consignee_name
     */

private String consigneeName;

    /**
     * 收货人地址       db_column: consignee_address
     */

private String consigneeAddress;

    /**
     * 下单时间       db_column: create_time
     */

private Date createTime;

    /**
     * 支付时间       db_column: pay_time
     */

private Date payTime;

    /**
     * 快递       db_column: logisticid
     */

private Integer logisticid;

    /**
     * 快递单号       db_column: logistic_no
     */

private String logisticNo;







}






