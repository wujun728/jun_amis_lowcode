package com.jun.plugin.business.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import com.jun.plugin.system.vo.BaseVo;

import java.util.Date;

/**
 * 
 * 
 * ClassName: InportVo create: 2020-04-26 17:51
 *
 * @author: Wujun @since： JDK1.8
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class OutportVo extends BaseVo {
	/**
	 * 供应商ID
	 */
	private Integer providerid;
	/**
	 * 商品ID
	 */
	private Integer goodsid;
	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:dd")
	private Date endTime;
	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:dd")
	private Date startTime;

}
