package com.jun.biz.manager.dto.role;

import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
public class CreateRoleDTO  {



	
	private String name;
	
	private String description;
	


	private Integer sort;

	private Set<Long> permissionIds;

	
}
