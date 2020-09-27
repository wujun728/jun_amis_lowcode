package com.puboot.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */

@Setter
@Getter
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code = 500;
    private String msg;

    public AppException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AppException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public AppException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public AppException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public AppException(ExceptionEnum exceptionEnum) {
        String msg = exceptionEnum.getMsg();
        Integer code = exceptionEnum.getCode();
        this.msg = msg;
        this.code = code;
    }


}
