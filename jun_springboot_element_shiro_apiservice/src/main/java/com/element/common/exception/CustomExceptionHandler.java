package com.element.common.exception;

import com.element.common.utils.ResultVo;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ResultVo handleCustomException(CustomException e) {
        ResultVo resultVo = new ResultVo();
        resultVo.put("code", e.getCode());
        resultVo.put("msg", e.getMessage());
        return resultVo;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVo handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultVo.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultVo handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResultVo.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResultVo handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        return ResultVo.error(403,"没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public ResultVo handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultVo.error();
    }

}
