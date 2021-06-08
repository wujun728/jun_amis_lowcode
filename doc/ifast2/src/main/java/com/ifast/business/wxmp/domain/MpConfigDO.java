package com.ifast.business.wxmp.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;



/**
 * 
 * <pre>
 * 微信配置表
 * </pre>
 * <small> 2018-04-11 23:27:06 | Aron</small>
 */
 @TableName("wx_mp_config")
 @Data
public class MpConfigDO implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    
    /** 主键ID */
    @TableId
    private Integer id;
    /** token */
    private String token;
    /** APPID */
    @TableField("app_id")
    private String appId;
    /** AppSecret */
    @TableField("app_secret")
    private String appSecret;
    /** 1加密 0不加密 */
    @TableField("msg_mode")
    private Integer msgMode;
    /** 秘钥 */
    @TableField("msg_secret")
    private String msgSecret;
    /** 公众号名字 */
    @TableField("mp_name")
    private String mpName;
    /** 公众号类型： 1.订阅号 2.服务号 3.企业号 4.小程序 5. 测试号 */
    @TableField("app_type")
    private Integer appType;
    /** 认证授权：1已认证 0未认证 */
    @TableField("is_auth")
    private Integer isAuth;
    /** 提示订阅URL */
    
    @TableField("subscribe_url")
    private String subscribeUrl;
    /** logo */
    private String logo;
    /** 创建时间 */
    @TableField("create_time")
    private Date createTime;

}
