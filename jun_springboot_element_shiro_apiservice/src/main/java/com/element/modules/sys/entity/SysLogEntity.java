package com.element.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 */
@TableName("sys_log")
@ApiModel(value = "SysLogEntity对象", description = "系统日志")
public class SysLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户操作")
    @TableField("operation")
    private String operation;

    @ApiModelProperty(value = "请求方法")
    @TableField("method")
    private String method;

    @ApiModelProperty(value = "请求参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "执行时长(毫秒)")
    @TableField("time")
    private Long time;

    @ApiModelProperty(value = "IP地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
