package com.qingshop.mall.common.constant;

/**
 * 通用常量信息
 */
public class Constants {
	/**
	 * UTF-8 字符集
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * STATUS_VALID=0 (Integer类型值1)
	 */
	public static final Integer STATUS_VALID = 1;

	/**
	 * STATUS_INVALID=0 (Integer类型值0)
	 */
	public static final Integer STATUS_INVALID = 0;

	/**
	 * STATUS_VALID_STRING=0 (字符串值 "1")
	 */
	public static final String STATUS_VALID_STRING = "1";

	/**
	 * STATUS_INVALID_STRING=0 (字符串值 "0")
	 */
	public static final String STATUS_INVALID_STRING = "0";

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

	/**
	 * 文件存放文件夹名称
	 */
	public static final String FILE_ = "file";

	/**
	 * 本地文件上传路径
	 */
	public static final String WORK_DIR_KEY = "global.workDir";
}
