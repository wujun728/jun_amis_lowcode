package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 商品基本信息表
 */
@TableName("mall_goods")
public class MallGoods implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("goods_id")
    private Long goodsId;

    /**
     * 商品编号
     */
    @TableField("good_sn")
    private String goodSn;

    /**
     * 商品名称
     */
    @TableField("good_name")
    private String goodName;

    /**
     * 商品所属类目ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 商品关键字，采用逗号间隔
     */
    private String keywords;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    private String gallery;

    /**
     * 商品简介
     */
    private String brief;

    /**
     * 商品页面商品图片
     */
    @TableField("pic_url")
    private String picUrl;

    /**
     * 商品单位，例如件、盒
     */
    private String unit;

    /**
     * 划线价格
     */
    @TableField("line_price")
    private BigDecimal linePrice;

    /**
     * 零售价格
     */
    @TableField("good_price")
    private BigDecimal goodPrice;

    /**
     * 商品详细介绍，是富文本格式
     */
    private String detail;

    /**
     * 商品规格(1单规格 2多规格)
     */
    @TableField("spec_type")
    private Integer specType;

    /**
     * 是否上架
     */
    @TableField("is_shelf")
    private Integer isShelf;

    /**
     * 是否新品首发首页展示
     */
    @TableField("is_new")
    private Integer isNew;

    /**
     * 是否人气推荐首页展示
     */
    @TableField("is_hot")
    private Integer isHot;

    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * 逻辑删除
     */
    private Integer deleted;

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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public String getGoodSn() {
        return goodSn;
    }

    public void setGoodSn(String goodSn) {
        this.goodSn = goodSn;
    }
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public BigDecimal getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
    }
    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Integer getSpecType() {
        return specType;
    }

    public void setSpecType(Integer specType) {
        this.specType = specType;
    }
    public Integer getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Integer isShelf) {
        this.isShelf = isShelf;
    }
    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }
    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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
        return "MallGoods{" +
        "goodsId=" + goodsId +
        ", goodSn=" + goodSn +
        ", goodName=" + goodName +
        ", categoryId=" + categoryId +
        ", keywords=" + keywords +
        ", gallery=" + gallery +
        ", brief=" + brief +
        ", picUrl=" + picUrl +
        ", unit=" + unit +
        ", linePrice=" + linePrice +
        ", goodPrice=" + goodPrice +
        ", detail=" + detail +
        ", specType=" + specType +
        ", isShelf=" + isShelf +
        ", isNew=" + isNew +
        ", isHot=" + isHot +
        ", sortOrder=" + sortOrder +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
