package com.jun.biz.manager.dto.role;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import com.jun.biz.common.base.dto.PageDTO;

/**
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListRoleDTO extends PageDTO {


    private Integer id;

    private String name;

    private String description;


    private Date minCreateTime;
    private Date maxCreateTime;




}
