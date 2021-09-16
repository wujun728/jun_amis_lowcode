package com.jun.plugin.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * ClassName: LoginfoVo Description: layui date: 2020/4/15 19:17
 *
 * @since JDK 1.8 部门增强类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends BaseVo {

	/**
	 * 部门标题
	 */
	private String title;

	/**
	 * 结束时间 前端获取的时间转换一下
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 开始时间 前端获取的时间转换一下
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

}