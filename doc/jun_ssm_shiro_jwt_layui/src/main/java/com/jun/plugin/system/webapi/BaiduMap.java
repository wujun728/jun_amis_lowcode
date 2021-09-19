package com.jun.plugin.system.webapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: BaiduMap Description: layui date: 2020/4/15 17:01
 *
 * 
 * 
 * @since JDK 1.8 组装百度地图 接收JSON
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiduMap {

	private String address;
	private Map<String, String> content = new HashMap<>();
	private Integer status;

	/*
	 * 
	 * public static class mapContent { private String address; private Map<String,
	 * String> address_detail; private Map<String, String> point; }
	 */

}