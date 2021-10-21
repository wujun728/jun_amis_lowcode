		package com.jun.biz.manager.dto.admin;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.jun.biz.common.base.dto.PageDTO;

import java.util.Date;

@Data
public class ListAdminLoginRecordDTO extends PageDTO {


    private Integer id;

    private Integer adminId;

    private String adminUserName;

    private String ip;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxCreateTime;

    private String os;

    private String device;

    private String browser;


	
	
	
	
}
