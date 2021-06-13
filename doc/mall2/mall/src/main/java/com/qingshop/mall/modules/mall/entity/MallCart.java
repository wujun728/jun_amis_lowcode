package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 购物车商品表
 */
@TableName("mall_cart")
public class MallCart implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("cart_id")
    private Long cartId;

    /**
     * 用户表的用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 商品表的商品ID
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品货品表的货品ID
     */
    @TableField("skudetail_id")
    private Long skudetailId;

    /**
     * 商品货品的数量
     */
    private Integer number;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    private String specifications;

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
    
    
	//商品属性
    @TableField(exist = false)
	private String picUrl;
    @TableField(exist = false)
	private String goodsName;
    @TableField(exist = false)
	private BigDecimal goodPrice;
	

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public Long getSkudetailId() {
        return skudetailId;
    }

    public void setSkudetailId(Long skudetailId) {
        this.skudetailId = skudetailId;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
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

    public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(BigDecimal goodPrice) {
		this.goodPrice = goodPrice;
	}

	@Override
    public String toString() {
        return "MallCart{" +
        "cartId=" + cartId +
        ", userId=" + userId +
        ", goodsId=" + goodsId +
        ", skudetailId=" + skudetailId +
        ", number=" + number +
        ", specifications=" + specifications +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
