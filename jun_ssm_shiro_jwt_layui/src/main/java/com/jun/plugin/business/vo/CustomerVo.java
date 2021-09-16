package com.jun.plugin.business.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import com.jun.plugin.system.vo.BaseVo;

import java.util.Date;

/**
 * ClassName: LoginfoVo Description: layui date: 2020/4/15 19:17
 *
 * 
 * 
 * @since JDK 1.8 公告增强类 模糊查询
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerVo extends BaseVo {

	/**
	 * 客户名称
	 */
	private String customername;
	/**
	 * 联系人电话
	 */
	private String phone;
	/**
	 * 联系人
	 */
	private String connectionperson;

}