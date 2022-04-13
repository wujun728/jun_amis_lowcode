package com.element.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户Token
 */
@TableName("sys_user_token")
@ApiModel(value = "SysUserTokenEntity对象", description = "系统用户Token")
public class SysUserTokenEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //用户ID
    @TableId(type = IdType.INPUT)
    private Long userId;

    @ApiModelProperty(value = "token")
    @TableField("token")
    private String token;

    @ApiModelProperty(value = "过期时间")
    @TableField("expire_time")
    private Date expireTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
