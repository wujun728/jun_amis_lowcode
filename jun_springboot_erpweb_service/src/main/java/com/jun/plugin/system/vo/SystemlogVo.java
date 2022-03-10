package com.jun.plugin.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * ClassName: LoginfoVo Description: layui date: 2020/4/15 19:17
 *
 * @since JDK 1.8 公告增强类 模糊查询
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemlogVo extends BaseVo {

	/**
	 * 操作人员
	 */
	private String thisName;
	/**
	 * IP
	 */
	private String ip;
	/**
	 * 地址
	 */
	private String address;
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