package com.jun.biz.manager.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateCategoryDTO {

	@NotNull
	private Long pid;

	@NotBlank
	private String name;

	private String description;
	@NotNull
	private Double weight;


}
