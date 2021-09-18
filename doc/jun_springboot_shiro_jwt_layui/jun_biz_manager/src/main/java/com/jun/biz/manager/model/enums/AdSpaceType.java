package com.jun.biz.manager.model.enums;

import com.jun.biz.common.utils.EnumUtil;

import lombok.Getter;

@Getter
public enum AdSpaceType {
    /**
     *
     */
    PRODUCT(1, "商品"), PIC(2, "图片"), PIC_TEXT(3, "图文"), LINK(4, "链接");
    private final Integer code;
    private final String desc;


    AdSpaceType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
        EnumUtil.putEnum(code, this);

    }

    public static AdSpaceType getByCode(int code) {
        return EnumUtil.getEnum(AdSpaceType.class, code);
    }
}
