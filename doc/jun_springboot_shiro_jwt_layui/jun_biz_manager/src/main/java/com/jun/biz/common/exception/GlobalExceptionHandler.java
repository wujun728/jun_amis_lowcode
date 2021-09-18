package com.jun.biz.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.biz.common.base.enums.VoCodeEnum;
import com.jun.biz.common.base.vo.ResultVO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * Created on 2018/3/27 15:05
 * <p>
 * Description: []
 * <p>
 *
 * 
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResultVO<String>> validationExceptionHandle(BindException exception) {
        StringBuilder msg = new StringBuilder();
        exception.getAllErrors().forEach(objectError -> {
            log.warn("参数异常！{}", objectError);
            msg.append(objectError.getDefaultMessage()).append(",");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultVO<>(VoCodeEnum.ILLEGAL_ARGUMENTS.getCode(), msg.toString()));
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResultVO<String>> validationExceptionHandle(ValidationException exception) {
        StringBuilder msg = new StringBuilder();
        if (exception instanceof ConstraintViolationException) {
            for (ConstraintViolation<?> constraintViolation : ((ConstraintViolationException) exception).getConstraintViolations()) {
                msg.append(constraintViolation.getMessage()).append(",");
            }
        }
        log.warn("参数异常！msg={}", msg.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultVO<>(VoCodeEnum.ILLEGAL_ARGUMENTS.getCode(), msg.toString()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResultVO<String>> validationExceptionHandle(MethodArgumentNotValidException exception) {
        StringBuilder msg = new StringBuilder();
        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            log.warn("参数异常！{}", objectError);
            msg.append(objectError.getDefaultMessage()).append(",");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultVO<>(VoCodeEnum.ILLEGAL_ARGUMENTS.getCode(), msg.toString()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ResultVO<String>> exceptionHandle(HttpRequestMethodNotSupportedException e) {
        log.info("http请求不支持该方法", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultVO<>(VoCodeEnum.ILLEGAL_ARGUMENTS.getCode(), "http请求不支持该方法"));

    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ResultVO<String>> exceptionHandle(HttpMediaTypeNotSupportedException e) {
        log.info("Content type 错误", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultVO<>(VoCodeEnum.ILLEGAL_ARGUMENTS.getCode(), "content type 不支持！" + e.getContentType()));

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<ResultVO<String>> exceptionHandle(MissingServletRequestParameterException e) {
        log.info("缺少必要参数", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultVO<>(VoCodeEnum.ILLEGAL_ARGUMENTS.getCode(), e.getParameterName() + "不能为空"));

    }

    /**
     * 请求体为空或者请求体json格式化错误会抛此异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ResultVO<String>> exceptionHandle(HttpMessageNotReadableException e) {
        log.warn("HttpMessageNotReadableException", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultVO<>(VoCodeEnum.ILLEGAL_ARGUMENTS.getCode(), "请求体json格式化错误"));

    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResultVO<String>> unknownExceptionHandle(Exception exception) {
        log.error("未知异常！", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultVO.buildFailResult(exception.getMessage()));
    }


}
