package com.jun.biz.common.utils;

/**
 * @author liuhui
 * @version 1.0
 * @date 2013-6-6 下午8:34:35
 */
public class ValidateUtil {

    public static final String EMAIL_REGEX = "(^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)?";
    public static final String CELLPHONE_REGEX = "(^1[3458]\\d{9}$)?";
    public static final String USERNAME_REGEX = "(^[\u0391-\uFFE5a-zA-Z_]{1}[\u0391-\uFFE5a-zA-Z_0-9]{2,19}$)?";
    public static final String REAL_NAME = "(^[\u4e00-\u9fa5]{2,5}$|(^[a-zA-Z]+[\\s.]?([a-zA-Z]+[\\s.]?){0,4}[a-zA-Z]$))?";
    public static final String TEL = "(^(0[1,2]{1}\\d{1}-?\\d{8})$|^(0[3-9]{1}\\d{2}-?\\d{7,8})$)?";


    public static boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean validateCellphone(String telphone) {
        return telphone.matches(CELLPHONE_REGEX);
    }

    public static boolean validateUsername(String username) {
        return username.matches(USERNAME_REGEX);
    }

    public static boolean validateRealName(String name) {
        return name.matches(REAL_NAME);
    }

    public static boolean validateTel(String tel) {
        return tel.matches(TEL);
    }

    public static void main(String[] args) {
        System.out.println(validateCellphone(""));
    }
}
