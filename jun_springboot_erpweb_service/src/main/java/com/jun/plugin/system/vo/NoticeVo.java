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
public class NoticeVo extends BaseVo {

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 操作员
	 */
	private String opername;
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