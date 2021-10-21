package com.jun.biz.common.base.enums;


import com.jun.biz.common.utils.EnumUtil;

import lombok.Getter;
import lombok.ToString;

/**
 * Created on 2018/1/19 10:46
 * <p>
 * Description: [返回code对照表]
 * <p>
 *
 * 
 */
@Getter
@ToString
public enum VoCodeEnum {
    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),
    FAIL(400, "请求失败"),
    ILLEGAL_ARGUMENTS(401, "参数异常"),
    RETRY_LATER(402, "失败稍后重试"),
    OTHER(500, "其他异常"),


    NOT_LOGGED_IN(1001, "用户未登录"),
    UNAUTHORIZED(1002, "用户未授权"),
    USER_STATUS_ERROR(1003, "用户被冻结"),
    PERMISSION_CENTER_ERROR(1004, "权限中心接口失败"),
    NO_PERMISSION(1005, "权限不足"),
    PERMISSION_CENTER_UNAUTHORIZED(1006, "很遗憾，您暂时没有该系统的权限"),
    PASSWORD_ERROR(1007, "用户名或密码错误"),


    CAPTCHA_ERROR(1008,"验证码错误"),
    USER_NAME_EXIST(1009,"用户名存在"),
    EMAIL_EXIST(1010,"邮箱已存在"),

    NOT_FOUND_PRODUCT(2001,"找不到产品"),
    ;

    private final int code;
    private final String msg;

    VoCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
        EnumUtil.putEnum(code, this);
    }

    public static VoCodeEnum getByCode(int code) {
        return EnumUtil.getEnum(VoCodeEnum.class, code);
    }

    public static String toDescString() {
        StringBuilder str = new StringBuilder();
        str.append("code码|描述\n| :---: | :--- |\n");
        for (VoCodeEnum voCodeEnum : VoCodeEnum.values()) {
            str.append(voCodeEnum.getCode()).append("|").append(voCodeEnum.getMsg()).append("\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(VoCodeEnum.toDescString());
    }


    }
