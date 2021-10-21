package com.jun.biz.manager.dto.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import com.jun.biz.common.base.dto.PageDTO;

import java.util.Date;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class ListAdminDTO extends PageDTO {



    private String username;


    private String realName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minLastLoginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxLastLoginTime;


    private Integer status;


}
