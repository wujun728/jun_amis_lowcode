package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 物流公司
 */
@TableName("mall_delivery_company")
public class MallDeliveryCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("dvy_id")
    private Long dvyId;

    /**
     * 物流公司名称
     */
    @TableField("dvy_name")
    private String dvyName;

    /**
     * 公司编码
     */
    @TableField("dvy_code")
    private String dvyCode;

    /**
     * 公司主页
     */
    @TableField("company_url")
    private String companyUrl;

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

    public Long getDvyId() {
        return dvyId;
    }

    public void setDvyId(Long dvyId) {
        this.dvyId = dvyId;
    }
    public String getDvyName() {
        return dvyName;
    }

    public void setDvyName(String dvyName) {
        this.dvyName = dvyName;
    }
    public String getDvyCode() {
        return dvyCode;
    }

    public void setDvyCode(String dvyCode) {
        this.dvyCode = dvyCode;
    }
    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
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
        return "MallDeliveryCompany{" +
        "dvyId=" + dvyId +
        ", dvyName=" + dvyName +
        ", dvyCode=" + dvyCode +
        ", companyUrl=" + companyUrl +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
