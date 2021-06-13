package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 配送设置表
 */
@TableName("mall_ship_set")
public class MallShipSet implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("set_id")
    private Long setId;

    /**
     * 销售区域（1-部分区域，0-全国）
     */
    @TableField("sale_area_type")
    private Integer saleAreaType;

    /**
     * 是否自提
     */
    @TableField("is_self_mention")
    private Integer isSelfMention;

    /**
     * 运费方式（1-自定义邮费，0-卖家包邮）
     */
    @TableField("freight_type")
    private Integer freightType;

    /**
     * 首重 (strategy = FieldStrategy.IGNORED 更新字段为 null)
     */
    @TableField(value="default_weight", updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal defaultWeight;

    /**
     * 首重费 (strategy = FieldStrategy.IGNORED 更新字段为 null)
     */
    @TableField(value="default_expense", updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal defaultExpense;

    /**
     * 续重
     */
    @TableField("continue_weight")
    private BigDecimal continueWeight;

    /**
     * 续重费 (strategy = FieldStrategy.IGNORED 更新字段为 null)
     */
    @TableField(value="continue_expense", updateStrategy = FieldStrategy.IGNORED)
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

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }
    public Integer getSaleAreaType() {
        return saleAreaType;
    }

    public void setSaleAreaType(Integer saleAreaType) {
        this.saleAreaType = saleAreaType;
    }
    public Integer getIsSelfMention() {
        return isSelfMention;
    }

    public void setIsSelfMention(Integer isSelfMention) {
        this.isSelfMention = isSelfMention;
    }
    public Integer getFreightType() {
        return freightType;
    }

    public void setFreightType(Integer freightType) {
        this.freightType = freightType;
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
        return "MallShipSet{" +
        "setId=" + setId +
        ", saleAreaType=" + saleAreaType +
        ", isSelfMention=" + isSelfMention +
        ", freightType=" + freightType +
        ", defaultWeight=" + defaultWeight +
        ", defaultExpense=" + defaultExpense +
        ", continueWeight=" + continueWeight +
        ", continueExpense=" + continueExpense +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
