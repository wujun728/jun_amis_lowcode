package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 指定条件包邮表
 */
@TableName("mall_ship_free")
public class MallShipFree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("free_id")
    private Long freeId;

    /**
     * 包邮区域省市名称
     */
    @TableField("freeship_area")
    private String freeshipArea;

    /**
     * 包邮区域(城市id集)
     */
    @TableField("freeship_ids")
    private String freeshipIds;

    /**
     * 满重量包邮(Kg)
     */
    @TableField("weight_free")
    private BigDecimal weightFree;

    /**
     * 满金额包邮(元)
     */
    @TableField("expense_fee")
    private BigDecimal expenseFee;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    public Long getFreeId() {
        return freeId;
    }

    public void setFreeId(Long freeId) {
        this.freeId = freeId;
    }
    public String getFreeshipArea() {
        return freeshipArea;
    }

    public void setFreeshipArea(String freeshipArea) {
        this.freeshipArea = freeshipArea;
    }
    public String getFreeshipIds() {
        return freeshipIds;
    }

    public void setFreeshipIds(String freeshipIds) {
        this.freeshipIds = freeshipIds;
    }
    public BigDecimal getWeightFree() {
        return weightFree;
    }

    public void setWeightFree(BigDecimal weightFree) {
        this.weightFree = weightFree;
    }
    public BigDecimal getExpenseFee() {
        return expenseFee;
    }

    public void setExpenseFee(BigDecimal expenseFee) {
        this.expenseFee = expenseFee;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MallShipFree{" +
        "freeId=" + freeId +
        ", freeshipArea=" + freeshipArea +
        ", freeshipIds=" + freeshipIds +
        ", weightFree=" + weightFree +
        ", expenseFee=" + expenseFee +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
