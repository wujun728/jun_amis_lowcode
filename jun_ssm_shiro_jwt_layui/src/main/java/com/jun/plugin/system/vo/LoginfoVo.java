package com.jun.plugin.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import com.jun.plugin.system.domain.Loginfo;

import java.util.Date;

/**
 * ClassName: LoginfoVo Description: layui date: 2020/4/15 19:17
 *
 * @since JDK 1.8 日志增强类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginfoVo extends BaseVo {

	/**
	 * 登陆名称
	 */
	private String loginname;
	/**
	 * 登陆ip
	 */
	private String loginip;
	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

}