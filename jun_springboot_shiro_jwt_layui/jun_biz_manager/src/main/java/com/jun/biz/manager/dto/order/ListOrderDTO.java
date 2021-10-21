package com.jun.biz.manager.dto.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

import com.jun.biz.common.base.dto.PageDTO;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class ListOrderDTO extends PageDTO {


    private Integer id;

    private String no;

    private Integer status;

    private Double totalAmount;

    private Double payAmount;

    private Double refundAmount;

    private Integer totalQuantity;

    private Integer consumerUserId;

    private String consigneeTel;

    private String consigneeCellphone;

    private String consigneeName;

    private String consigneeAddress;

    private Date minCreateTime;
    private Date maxCreateTime;

    private Date minPayTime;
    private Date maxPayTime;

    private Integer logisticid;

    private String logisticNo;


}
