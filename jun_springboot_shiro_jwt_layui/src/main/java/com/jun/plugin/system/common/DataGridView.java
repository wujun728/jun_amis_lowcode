package com.jun.plugin.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: DateGridView Description: layui date: 2020/4/15 19:26
 *
 * 
 * 
 * @since JDK 1.8 组装layui返回数据格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView implements Serializable {

	private Integer code = 0;
	private String msg = "";
	private Long count;
	private Object data;

	public DataGridView(Object data) {
		this.data = data;
	}

	public DataGridView(Long count, Object data) {
		this.count = count;
		this.data = data;
	}

}