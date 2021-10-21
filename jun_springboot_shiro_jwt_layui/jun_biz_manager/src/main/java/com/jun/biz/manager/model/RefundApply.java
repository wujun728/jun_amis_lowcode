package com.jun.biz.manager.model;


import lombok.Data;

import java.util.Date;

import com.jun.biz.manager.model.annotation.Pk;

/**
 * 
 */

@Data
public class RefundApply  {




    /**
     * id       db_column: id
     */

    @Pk
	private Long id;

    /**
     * 单订号       db_column: order_id
     */

private Integer orderId;

    /**
     * 退款金额       db_column: amount
     */

private Double amount;

    /**
     * 退款原因       db_column: reason
     */

private String reason;

    /**
     * 状态       db_column: status
     */

private Integer status;

    /**
     * createTime       db_column: create_time
     */

private Date createTime;

    /**
     * 理员管回复       db_column: reply
     */

private String reply;

    /**
     * replyTime       db_column: reply_time
     */

private Date replyTime;

    /**
     * replayAdminId       db_column: replay_admin_id
     */

private Integer replayAdminId;

    /**
     * pics       db_column: pics
     */

private String pics;







}






