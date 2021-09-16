package com.jun.plugin.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * ClassName: LoginfoVo Description: layui date: 2020/4/15 19:17
 *
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends BaseVo {

	private String name;
	private String remark;
	private String address;
	/**
	 * 部门ID
	 */
	private Integer deptid;

}