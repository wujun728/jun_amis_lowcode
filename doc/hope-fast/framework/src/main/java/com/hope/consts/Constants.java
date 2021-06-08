package com.hope.consts;

/**
 * 通用常量信息
 *
 * @author aodeng
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;
    /**
     * 失败标记
     */
    public static final Integer FAIL = 501;

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 图片验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 短信验证码 redis key
     */
    public static final String SMS_CODE_KEY = "sms_codes:";

    /**
     * "authtype":101-短信登录 102-注册 103-修改密码 104-修改手机号
     */
    public static final String SMS_CODE_KEY_AUTHTYPE_1 = "101";
    public static final String SMS_CODE_KEY_AUTHTYPE_2 = "102";
    public static final String SMS_CODE_KEY_AUTHTYPE_3 = "103";
    public static final String SMS_CODE_KEY_AUTHTYPE_4 = "104";

    /**
     * 图片验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 短信验证码有效期（分钟）
     */
    public static final Integer SMS_CAPTCHA_EXPIRATION = 5;

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 资源服务搭建：https://juejin.cn/post/6844903892073840653
     * 资源服务器路径(nginx代理访问资源服务图片的地址 举例：访问ftp.aodeng.cc代理(nginx)到linux服务器/var/ftp/hope目录下)
     */
    public static final String RESOURCE_SERVER_PATH = "http://ftp.aodeng.cc:8002";

    /**
    * ftp搭建：https://cloud.tencent.com/document/product/213/10912
    * ftp配置：默认上传路径 服务器地址 登录账号 登录密码 端口号(默认21)
    */
    public static final String FTP_DEFAULT_BASE_DIR="/var/ftp/hope";
    public static final String FTP_HOSTNAME="";
    public static final String FTP_USERNAME="ftpuseraodeng";
    public static final String FTP_PASSWORD="ftpuseraodeng";
    public static final Integer FTP_PORT=21;

    /**
     * 登录成功用户token-redis保存用户信息有效期（天）
     */
    public static final Integer TOKEN_REDIS_USERINFO_EXPIRATION = 30;

    /**
     * MD5 加密盐值 salt
     */
    public static final String MD5_SALT = "yt_device_app";
}
