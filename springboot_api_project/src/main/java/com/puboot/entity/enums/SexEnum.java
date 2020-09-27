package com.puboot.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: linzhaoguan
 * Date: 2019-08-30
 * Time: 4:43 下午
 */
@AllArgsConstructor
public enum SexEnum {

    /**
     * 男
     */
    MAN(1, "男"),
    /**
     * 女
     */
    WOMAN(2, "女"),
    /**
     * 未知
     */
    NONE(0, "未知");

    @EnumValue
    @Getter
    private final Integer code;

    @Getter
    private final String desc;

}
