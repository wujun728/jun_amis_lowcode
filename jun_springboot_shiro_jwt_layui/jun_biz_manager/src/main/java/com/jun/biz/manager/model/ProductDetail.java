package com.jun.biz.manager.model;

import com.jun.biz.manager.model.annotation.Pk;

import lombok.Data;

@Data
public class ProductDetail {
    @Pk
    private Long id;
    private Long productId;
    private String detail;

}
