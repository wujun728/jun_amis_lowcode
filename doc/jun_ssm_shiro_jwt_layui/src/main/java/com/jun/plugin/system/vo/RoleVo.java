package com.jun.plugin.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName: LoginfoVo Description: layui date: 2020/4/15 19:17
 *
 * @since JDK 1.8 部门增强类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends BaseVo {

	private Integer userId;

	private String name;
	private String remark;

}