package com.puboot.entity;

import com.puboot.entity.enums.SexEnum;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别(1男;2女;0未知)
     */
    private SexEnum sex;

    /**
     * 账户是否有效
     */
    private Integer visibleState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除状态(0未删除;1已删除)
     */
    private Boolean delState;
}

