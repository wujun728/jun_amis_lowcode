package com.jun.plugin.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/18
 *
 * 
 * 
 * @since JDK 1.8
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
public class Menu implements Serializable {
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 父级菜单ID
	 */
	@TableField(value = "pid")
	private Integer pid;

	/**
	 * 类型[topmenu/leftmenu/permission]
	 */
	@TableField(value = "type")
	private String type;

	/**
	 * topmenu:system/business permission:menu:addMenu
	 */
	@TableField(value = "typecode")
	private String typecode;

	/**
	 * 名称
	 */
	@TableField(value = "title")
	private String title;

	/**
	 * 图标
	 */
	@TableField(value = "icon")
	private String icon;

	/**
	 * 连接地址
	 */
	@TableField(value = "href")
	private String href;

	@TableField(value = "target")
	private String target;

	/**
	 * 是否展开
	 */
	@TableField(value = "spread")
	private Integer spread;

	/**
	 * TableField(exist = false) 不存在处理一下 表示 数据库当中没有这个 字段 让mq 忽略
	 */
//	  @TableField(exist = false)
//	  private Boolean open;

	/**
	 * 排序码
	 */
	@TableField(value = "ordernum")
	private Integer ordernum;

	/**
	 * 状态【0不可用1可用】
	 */
	@TableField(value = "available")
	private Integer available;

	/**
	 * 重写判断 前端table 是否展开
	 */
//	  public Boolean getOpen() {
//			return this.spread == 1 ? true : false;
//	  }
	/***
	 * 构造 权限
	 * 
	 * @param id
	 * @param pid
	 * @param type
	 * @param typeCode
	 * @param title
	 */
	public Menu(Integer id, Integer pid, String type, String typeCode, String title, Integer ordernum,
			Integer available) {
		this.id = id;
		this.pid = pid;
		this.type = type;
		this.typecode = typeCode;
		this.title = title;
		this.ordernum = ordernum;
		this.available = available;
	}

	private static final long serialVersionUID = 1L;

	public static final String COL_ID = "id";

	public static final String COL_PID = "pid";

	public static final String COL_TYPE = "type";

	public static final String COL_TYPECODE = "typecode";

	public static final String COL_TITLE = "title";

	public static final String COL_ICON = "icon";

	public static final String COL_HREF = "href";

	public static final String COL_TARGET = "target";

	public static final String COL_SPREAD = "spread";

	public static final String COL_ORDERNUM = "ordernum";

	public static final String COL_AVAILABLE = "available";
}