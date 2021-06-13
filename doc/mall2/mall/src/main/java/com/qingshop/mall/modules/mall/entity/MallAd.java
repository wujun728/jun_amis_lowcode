package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 广告表
 */
@TableName("mall_ad")
public class MallAd implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ad_id")
    private Long adId;

    /**
     * 广告标题
     */
    @TableField("ad_name")
    private String adName;

    /**
     * 链接地址
     */
    @TableField("link_url")
    private String linkUrl;

    /**
     * 广告宣传图片
     */
    @TableField("ad_url")
    private String adUrl;

    /**
     * 广告位置：1则是首页
     */
    private Integer position;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 广告开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 广告结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 是否启动
     */
    private Integer enabled;

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

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }
    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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
        return "MallAd{" +
        "adId=" + adId +
        ", adName=" + adName +
        ", linkUrl=" + linkUrl +
        ", adUrl=" + adUrl +
        ", position=" + position +
        ", content=" + content +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", enabled=" + enabled +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
