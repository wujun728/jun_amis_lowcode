package com.jun.biz.manager.model.enums;

import lombok.Getter;

@Getter
public enum AdItemStatus {
    UNPUBLISHED(1, "未发布"), PUBLISHED(2, "已发布");
    private final Integer code;
    private final String desc;


    AdItemStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
