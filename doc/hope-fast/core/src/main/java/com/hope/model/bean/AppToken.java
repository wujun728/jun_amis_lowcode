package com.hope.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * app token
 *
 * @author aodeng
 */
@Data
@TableName("APP_TOKEN")
public class AppToken {

    /***主键*/
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * app_用户id
     */
    private String appUserId;
    /**
     * token
     */
    private String token;
    /**
     * token状态: 0:有效 1:失效
     */
    private String status;
    /**
     * 扩展字段 token类型（0:是app端 1:是pc端）
     */
    private String remark;
    /**
     * 有效期,到期redis自动失效，查询有效token需要做条件判断
     */
    private Date validPeriod;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date modifyTime;
}