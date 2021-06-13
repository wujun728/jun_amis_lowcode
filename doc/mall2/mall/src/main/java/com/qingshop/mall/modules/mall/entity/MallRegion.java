package com.qingshop.mall.modules.mall.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 省市区信息表
 */
@TableName("mall_region")
public class MallRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 简称
     */
    private String shortname;

    /**
     * 名称
     */
    private String name;

    /**
     * 全称
     */
    @TableField("merger_name")
    private String mergerName;

    /**
     * 层级 1 2 3 省市区县
     */
    private Integer level;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 长途区号
     */
    private String code;

    /**
     * 邮编
     */
    @TableField("zip_code")
    private String zipCode;

    /**
     * 首字母
     */
    private String first;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getMergerName() {
        return mergerName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "MallRegion{" +
        "id=" + id +
        ", pid=" + pid +
        ", shortname=" + shortname +
        ", name=" + name +
        ", mergerName=" + mergerName +
        ", level=" + level +
        ", pinyin=" + pinyin +
        ", code=" + code +
        ", zipCode=" + zipCode +
        ", first=" + first +
        ", lng=" + lng +
        ", lat=" + lat +
        "}";
    }
}
