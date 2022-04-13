package com.element.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 系统验证码
 */
@TableName("sys_captcha")
@ApiModel(value = "SysCaptchaEntity对象", description = "系统验证码")
public class SysCaptchaEntity {

    @ApiModelProperty(value = "uuid")
    @TableId(type = IdType.INPUT)
    private String uuid;

    @ApiModelProperty(value = "验证码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "过期时间")
    @TableField("expire_time")
    private Date expireTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

}
