package com.qingshop.mall.common.bean;

import java.util.HashMap;

/**
 * 
 * @Time：2017年9月6日 下午1:29:08
 * @version 1.0
 */
public class Rest extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	private long code = 0;

	private Object data;

	private String msg;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Rest() {
	}

	public Rest(long code, long count, Object data, String msg) {
		super.put("code", code);
		super.put("data", data);
		super.put("msg", msg);
	}

	public static Rest ok() {
		return new Rest(200, 0, null, "操作成功");
	}

	public static Rest ok(String msg) {
		return new Rest(200, 0, null, msg);
	}

	public static Rest okData(Object data) {
		return new Rest(200, 0, data, "ok");
	}

	public static Rest failure() {
		return new Rest(500, 0, null, "系统异常");
	}

	public static Rest failure(String msg) {
		return new Rest(500, 0, null, msg);
	}
	
	public static Rest failureData(String msg, Object data) {
		return new Rest(500, 0, data, msg);
	}

	public static Rest failure(long code, String msg) {
		return new Rest(code, 0, null, msg);
	}
	
}
