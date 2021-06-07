package com.zhonghe.active4j.core.util;

/**
 * 分页工具类
 * @author 38943
 *
 */
public class PagerUtil {

	public PagerUtil() {

	}
	
	/**
	 * 根据当前页  每页显示条数 计算当前行号
	 * @param l
	 * @param m
	 * @return
	 */
	public static int getFirstResult(int l, int m) {
		if(l < 1) {
			return 0;
		}
		return (l - 1) * m;
	}
}
