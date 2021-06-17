package com.jun.plugin.common.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.support.BindingAwareModelMap;

import com.alibaba.fastjson.JSONObject;

/**
 * CacheKeyUtil
 */
public class CacheKeyUtil {

	/**
	 * 获取方法参数组成的key
	 *
	 * @param params
	 *            参数数组
	 */
	public static String getMethodParamsKey(Object... params) {
		if (StringUtils.isEmpty(params)) {
			return "";
		}
		StringBuilder key = new StringBuilder("(");
		for (Object obj : params) {
			if (obj.getClass().equals(BindingAwareModelMap.class)) {
				continue;
			}
			key.append(JSONObject.toJSONString(obj).replaceAll("\"", "'"));
		}
		key.append(")");
		return key.toString();
	}

}
