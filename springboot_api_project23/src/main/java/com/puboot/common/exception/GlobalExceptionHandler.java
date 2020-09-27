package com.puboot.common.exception;


import com.puboot.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理器
 */

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({AppException.class})
    public R exception(AppException ex) {
        return R.error(ex.getCode(), ex.getMsg());
    }

    /*@ExceptionHandler({Exception.class})
    public R exception(Exception ex) {
        return R.error(ex.getMessage());
    }*/


}
