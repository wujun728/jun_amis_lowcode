package com.jun.plugin.common.util;

import java.util.List;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.vo.PageResultVo;
import com.jun.plugin.common.vo.ResponseVo;


public class ResultUtil {

	public static ResponseVo<?> success() {
		return vo(CoreConst.SUCCESS_CODE, null, null);
	}

	public static ResponseVo<?> success(String msg) {
		return vo(CoreConst.SUCCESS_CODE, msg, null);
	}

	public static ResponseVo<?> success(String msg, Object data) {
		return vo(CoreConst.SUCCESS_CODE, msg, data);
	}

	public static ResponseVo<?> error() {
		return vo(CoreConst.ERROR_CODE, null, null);
	}

	public static ResponseVo<?> error(String msg) {
		return vo(CoreConst.ERROR_CODE, msg, null);
	}

	public static ResponseVo<?> error(String msg, Object data) {
		return vo(CoreConst.ERROR_CODE, msg, data);
	}

	public static PageResultVo table(List<?> list, Long total) {
		return new PageResultVo(list, total);
	}

	public static ResponseVo<Object> vo(Integer status, String message, Object data) {
		return new ResponseVo<>(status, message, data);
	}
}
