package com.hope.exception;

/**
 * 验证码错误异常类
 *
 * @author aodeng
 */
public class CaptchaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
