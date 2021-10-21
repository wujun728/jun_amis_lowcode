package com.jun.biz.manager.model.enums;

import com.jun.biz.common.utils.EnumUtil;

import lombok.Getter;

/**
 * Created on 2019/6/13 14:02
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Getter
public enum DelStatus {

    /**
     * 有效
     */
    VALID(DelStatus.VALID_VALUE, "有效"),
    /**
     * 删除
     */
    DELETE(DelStatus.DEL_VALUE, "删除");

    public static final int VALID_VALUE = 1;
    public static final int DEL_VALUE = 2;

    private final int code;
    private final String desc;

    DelStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
        EnumUtil.putEnum(code, this);
    }
    public static DelStatus getByCode(int code) {
        return EnumUtil.getEnum(DelStatus.class, code);
    }
}
