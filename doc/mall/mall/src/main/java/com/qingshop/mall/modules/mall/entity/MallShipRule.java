package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 运费计算规则表
 */
@TableName("mall_ship_rule")
public class MallShipRule implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("rule_id")
    private Long ruleId;

    /**
     * 可配送区域省市名称
     */
    @TableField("ship_area")
    private String shipArea;

    /**
     * 可配送区域(城市id集)
     */
    @TableField("ship_ids")
    private String shipIds;

    /**
     * 首件(个)/首重(Kg)
     */
    @TableField("default_weight")
    private BigDecimal defaultWeight;

    /**
     * 运费(元)
     */
    @TableField("default_expense")
    private BigDecimal defaultExpense;

    /**
     * 续件/续重
     */
    @TableField("continue_weight")
    private BigDecimal continueWeight;

    /**
     * 续费(元)
     */
    @TableField("continue_expense")
    private BigDecimal continueExpense;

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

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
    public String getShipArea() {
        return shipArea;
    }

    public void setShipArea(String shipArea) {
        this.shipArea = shipArea;
    }
    public String getShipIds() {
        return shipIds;
    }

    public void setShipIds(String shipIds) {
        this.shipIds = shipIds;
    }
    public BigDecimal getDefaultWeight() {
        return defaultWeight;
    }

    public void setDefaultWeight(BigDecimal defaultWeight) {
        this.defaultWeight = defaultWeight;
    }
    public BigDecimal getDefaultExpense() {
        return defaultExpense;
    }

    public void setDefaultExpense(BigDecimal defaultExpense) {
        this.defaultExpense = defaultExpense;
    }
    public BigDecimal getContinueWeight() {
        return continueWeight;
    }

    public void setContinueWeight(BigDecimal continueWeight) {
        this.continueWeight = continueWeight;
    }
    public BigDecimal getContinueExpense() {
        return continueExpense;
    }

    public void setContinueExpense(BigDecimal continueExpense) {
        this.continueExpense = continueExpense;
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
        return "MallShipRule{" +
        "ruleId=" + ruleId +
        ", shipArea=" + shipArea +
        ", shipIds=" + shipIds +
        ", defaultWeight=" + defaultWeight +
        ", defaultExpense=" + defaultExpense +
        ", continueWeight=" + continueWeight +
        ", continueExpense=" + continueExpense +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
