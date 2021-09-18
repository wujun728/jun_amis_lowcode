package com.jun.biz.manager.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import com.jun.biz.common.base.dto.PageDTO;

import java.util.Date;

/**
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListProductDTO extends PageDTO {


    private String likeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxCreateTime;

    private String createAdmin;

    private Integer status;

    private Integer categoryId;

}
