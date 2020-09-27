package com.puboot.common.exception;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: LinZhaoguan
 * Date: 2018-07-18
 * Time: 21:05
 */
@Getter
public enum ExceptionEnum {

    USER_NOT_EXIST(1000, "用户不存在"),
    WRONG_PASSWORD(1001, "密码错误");

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
