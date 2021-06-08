package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 商品货品表
 */
@TableName("mall_goods_skudetail")
public class MallGoodsSkudetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("skudetail_id")
    private Long skudetailId;

    /**
     * 商品表的商品ID
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    private String skudetails;

    /**
     * 商品规格图片
     */
    @TableField("pic_url")
    private String picUrl;

    /**
     * 销售价
     */
    @TableField("good_price")
    private BigDecimal goodPrice;

    /**
     * 划线价
     */
    @TableField("line_price")
    private BigDecimal linePrice;

    /**
     * 商品库存
     */
    private Integer number;

    /**
     * 商品编码
     */
    @TableField("business_code")
    private String businessCode;

    /**
     * 商品重量
     */
    @TableField("good_weight")
    private BigDecimal goodWeight;

    /**
     * 规格值标识
     */
    @TableField("valueclass_type")
    private String valueclassType;

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

    /**
     * 逻辑删除
     */
    private Integer deleted;

    public Long getSkudetailId() {
        return skudetailId;
    }

    public void setSkudetailId(Long skudetailId) {
        this.skudetailId = skudetailId;
    }
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public String getSkudetails() {
        return skudetails;
    }

    public void setSkudetails(String skudetails) {
        this.skudetails = skudetails;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }
    public BigDecimal getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
    public BigDecimal getGoodWeight() {
        return goodWeight;
    }

    public void setGoodWeight(BigDecimal goodWeight) {
        this.goodWeight = goodWeight;
    }
    public String getValueclassType() {
        return valueclassType;
    }

    public void setValueclassType(String valueclassType) {
        this.valueclassType = valueclassType;
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
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "MallGoodsSkudetail{" +
        "skudetailId=" + skudetailId +
        ", goodsId=" + goodsId +
        ", skudetails=" + skudetails +
        ", picUrl=" + picUrl +
        ", goodPrice=" + goodPrice +
        ", linePrice=" + linePrice +
        ", number=" + number +
        ", businessCode=" + businessCode +
        ", goodWeight=" + goodWeight +
        ", valueclassType=" + valueclassType +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}";
    }
}
