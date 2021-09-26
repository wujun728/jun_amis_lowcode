package com.royal.app.common.exception;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.royal.app.common.page.ResultData;

/**
 * <p>File：GloablExceptionHandler.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年11月29日 下午6:34:26</p>
 * <p>Company:  </p>
 * @author zmr
 * @version 1.0
 */
@ControllerAdvice
public class GloablExceptionHandler
{
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultData handleException(Exception e)
    {
        String msg = e.getMessage();
        if (msg == null || msg.equals(""))
        {
            msg = "服务器出错";
        }
        return new ResultData(500, msg);
    }

    // 捕捉UnauthorizedException
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResultData handle401()
    {
        return new ResultData(401, "Unauthorized");
    }

    // 捕捉shiro的异常
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResultData handle401(ShiroException e)
    {
        return new ResultData(401, "authorized error");
    }
}