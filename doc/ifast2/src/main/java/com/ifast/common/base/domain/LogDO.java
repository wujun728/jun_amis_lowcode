package com.ifast.common.base.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
@TableName("sys_log")
@Data
public class LogDO extends Model<LogDO> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = -938654836571738415L;
    
    @TableId
    private Long id;

    @TableField("user_id")
    private Long userId;

    private String username;

    private String operation;

    private Integer time;

    private String method;

    private String params;

    private String ip;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}