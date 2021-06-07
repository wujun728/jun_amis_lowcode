package com.tantsian.boot.model;

import apijson.MethodAccess;
import apijson.framework.BaseModel;

import static apijson.RequestRole.*;

/**
 * @author LY
 * @description: TODO
 * @title: Stone
 * @projectName apijson-boot
 * @date 2020/11/30
 */
@MethodAccess(
        POST = {UNKNOWN, ADMIN},
        GET = {UNKNOWN,LOGIN,ADMIN},
        DELETE = {ADMIN}
)
public class Stone  extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Integer cost;
    private Integer price;
    private Integer length;
    private Integer width;
    private Integer height;
    private Float weight;
    private String creationDate;
    private String modifyDate;
    private String modifier;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
