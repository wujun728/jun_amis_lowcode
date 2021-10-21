		package com.jun.biz.manager.dto.role;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
public class ModifyRoleDTO  extends CreateRoleDTO{



	@NotNull
	private Long id;


}
