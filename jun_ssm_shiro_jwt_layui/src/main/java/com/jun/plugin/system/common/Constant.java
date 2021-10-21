package com.jun.plugin.system.common;

import java.io.Serializable;

/**
 * ClassName: Constant Description: layui date: 2020/4/14 23:20
 *
 * 
 * 
 * @since JDK 1.8 静态属性直接拿
 */

public class Constant implements Serializable {

	/**
	 * 用户类型
	 */
	public static final Integer USER_TYPE_SUPER = 0;
	public static final Integer USER_TYPE_NORMAL = 1;

	/**
	 * 可用类型
	 */
	public static final Integer AVAILABLE_TRUE = 1;
	public static final Integer AVAILABLE_FALSE = 0;

	/**
	 * 权限类型
	 */
	public static final String MENU_TYPE_TOP = "topmenu";
	public static final String MENU_TYPE_LEFT = "leftmenu";
	public static final String MENU_TYPE_PERMISSION = "permission";

	/**
	 * 是否展开
	 */
	public static final Integer SPREAD_TRUE = 1;
	public static final Integer SPREAD_FALSE = 0;

	/**
	 * 密码
	 */
	public static final String DEFAULT_PWD = "123456";

	/**
	 * 默认头像地址 http://nb666.top
	 * http://nb666.top/group1/M00/00/00/rBHkkV6f6SSAKajEAAhxrMIZycU316.jpg 小盆友的
	 * /group1/M00/00/00/rBHkkV6dtmuALC5bAAjYBWxrayQ371.gif
	 */
	public static final String DEFAULT_TITLE_IMAGE = "/group1/M00/00/00/rBHkkV6f6SSAKajEAAhxrMIZycU316.jpg";
	// 默认的 角色
	public static Integer[] DEFAULT_ROLE_NAME = new Integer[] { 17 };
}
