package com.jun.plugin.system.vo;

import lombok.Data;

/**
 * ClassName: BaseVo Description: layui date: 2020/4/15 19:17
 *
 * @since JDK 1.8 多个VO用到的属性
 */
@Data
public class BaseVo {
	private Integer page;
	private Integer limit;
	private Integer available;
}
