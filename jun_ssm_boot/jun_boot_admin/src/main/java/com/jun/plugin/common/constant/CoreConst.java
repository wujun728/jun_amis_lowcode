package com.jun.plugin.common.constant;

public class CoreConst {

	/**
	 * 成功结果编码：200
	 */
	public static final Integer SUCCESS_CODE = 200;

	/**
	 * 失败结果编码：500
	 */
	public static final Integer ERROR_CODE = 500;
	
	/**
	 * 成功结果码：1
	 */
	public static final Integer RESULT_SUCCESS_CODE = 200;

	/**
	 * 成功结果编码：0
	 */
	public static final Integer RESULT_FAIL_CODE = 0;
	
	
	/**
	 * 状态值：1
	 */
	public static final Integer STATUS_VALID = 1;
	
	/**
	 * 状态值：0
	 */
	public static final Integer STATUS_INVALID = 0;

	/**
	 * 顶层菜单ID：0L
	 */
	public static final Long TOP_MENU_ID = 0L;

	/**
	 * 顶层菜单名称："顶层菜单"
	 */
	public static final String TOP_MENU_NAME = "顶层菜单";
	
	/**
	 * 文件路径分割
	 */
	public static final String FILE_ = "file";

	/**
	 * 本地文件上传路径: "global.workDir"
	 */
	public static final String WORK_DIR_KEY = "global.workDir";

	/**
	 * LOCAL_OSS_TYPE=0 (OSS本地存储类型)
	 */
	public static final Integer LOCAL_OSS_TYPE = 0;

	/**
	 * QINIU_OSS_TYPE=1 (OSS七牛云存储类型)
	 */
	public static final Integer QINIU_OSS_TYPE = 1;

	/**
	 * ALI_OSS_TYPE=2 (OSS阿里云存储类型)
	 */
	public static final Integer ALI_OSS_TYPE = 2;

	/**
	 * TENCENT_OSS_TYPE=3 (OSS腾讯云存储类型)
	 */
	public static final Integer TENCENT_OSS_TYPE = 3;

}
