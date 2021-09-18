package com.jun.biz.manager.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class CreateUserDTO {


	private String username;

	private String email;

	private String password;

	private Integer type;

	private Integer status;

	private Date lastLoginTime;

	private Date registerTime;


}
