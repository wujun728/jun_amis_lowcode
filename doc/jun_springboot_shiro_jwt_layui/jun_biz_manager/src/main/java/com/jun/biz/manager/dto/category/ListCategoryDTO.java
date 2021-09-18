package com.jun.biz.manager.dto.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

import com.jun.biz.common.base.dto.PageDTO;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class ListCategoryDTO extends PageDTO {


    private Integer id;

    private Integer pid;

    private String name;

    private String description;

    private Double weight;

    private Integer status;

    private Date minCreateTime;
    private Date maxCreateTime;

    private String createAdmin;


}
