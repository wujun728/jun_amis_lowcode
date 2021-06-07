package com.active4j.hr.common.constant;


/**
 * 全局常量类
 * @author teli_
 *
 */
public class GlobalConstant {
	
	/**
	 * 验证码存放Key
	 */
	public static final String SESSION_KEY_OF_RAND_CODE = "randCode"; // todo 要统一常量

	/**
	 * 默认创建人
	 */
	public static final String Default_Create_Name = "system";
	
	/**
	 * 错误提示 联系管理员
	 */
	public static String ERROR_MSG = "操作失败，请联系管理员";
	
	/**
	 * 用户
	 */
	public static final String SESSION_USER = "user";
	
	/**
	 * 没有权限进行此操作
	 */
	public static final String Err_Msg_No_Auth = "没有权限进行此操作";
	public static final String Err_Msg_All = "系统错误，请联系管理员";
	
	/**
	 * 文件上传 保存方式 常量
	 */
	public static final String FILE_UPLOADER_SAVE_FILE = "0"; // 保存文件系统
	public static final String FILE_UPLOADER_SAVE_DB = "1"; // 保存数据库
	
	/**
	 * 文件上传 类型 常量
	 */
	public static final String FILE_UPLOADER_TYPE_IMAGE = "0"; // 图片
	public static final String FILE_UPLOADER_TYPE_OTHER = "1"; // 其他
	
	/**
	 * 文件保存入库配置地址
	 */
	public static final String CONFIG_FILE_SAVE_DB_URL = "func/upload/viewImage?n=";
	 
}
