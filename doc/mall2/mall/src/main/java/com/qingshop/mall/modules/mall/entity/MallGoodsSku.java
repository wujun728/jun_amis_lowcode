package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 商品规格表
 */
@TableName("mall_goods_sku")
public class MallGoodsSku implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("sku_id")
    private Long skuId;

    /**
     * 商品表的商品ID
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品规格名
     */
    private String skukey;

    /**
     * 商品规格值
     */
    private String skuvalue;

    /**
     * 规格名标识
     */
    @TableField("keyclass_type")
    private String keyclassType;

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

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public String getSkukey() {
        return skukey;
    }

    public void setSkukey(String skukey) {
        this.skukey = skukey;
    }
    public String getSkuvalue() {
        return skuvalue;
    }

    public void setSkuvalue(String skuvalue) {
        this.skuvalue = skuvalue;
    }
    public String getKeyclassType() {
        return keyclassType;
    }

    public void setKeyclassType(String keyclassType) {
        this.keyclassType = keyclassType;
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
        return "MallGoodsSku{" +
        "skuId=" + skuId +
        ", goodsId=" + goodsId +
        ", skukey=" + skukey +
        ", skuvalue=" + skuvalue +
        ", keyclassType=" + keyclassType +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}";
    }
}
