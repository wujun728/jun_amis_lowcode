package com.element.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 系统配置信息
 */
@TableName("sys_config")
public class SysConfigEntity {

    @TableId
    private Long id;

    @ApiModelProperty(value = "key")
    @NotBlank(message = "参数名不能为空")
    @TableField("param_key")
    private String paramKey;

    @ApiModelProperty(value = "value")
    @NotBlank(message = "参数值不能为空")
    @TableField("param_value")
    private String paramValue;

    @ApiModelProperty(value = "状态   0：隐藏   1：显示")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
