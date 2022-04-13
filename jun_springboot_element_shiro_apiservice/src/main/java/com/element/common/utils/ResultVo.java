package com.element.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class ResultVo extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	public ResultVo() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static ResultVo error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ResultVo error(String msg) {
		return error(500, msg);
	}
	
	public static ResultVo error(int code, String msg) {
		ResultVo resultVo = new ResultVo();
		resultVo.put("code", code);
		resultVo.put("msg", msg);
		return resultVo;
	}

	public static ResultVo ok(String msg) {
		ResultVo resultVo = new ResultVo();
		resultVo.put("msg", msg);
		return resultVo;
	}
	
	public static ResultVo ok(Map<String, Object> map) {
		ResultVo resultVo = new ResultVo();
		resultVo.putAll(map);
		return resultVo;
	}
	
	public static ResultVo ok() {
		return new ResultVo();
	}

	public ResultVo put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
