package com.jun.biz.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Around("execution (* com.jun.biz.manager.web.controller.*.*(..))")
    public Object logging(ProceedingJoinPoint jp) throws Throwable {
        long currentTime = System.currentTimeMillis();
        Object result = null;
        Exception exception = null;
        try {
            result = jp.proceed();
            return result;
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            try {
                long afterTime = System.currentTimeMillis();
                long elapseTime = afterTime - currentTime;
                String callMethod = jp.getTarget().getClass().getSimpleName() + "." + jp.getSignature().getName();
                String args = Arrays.toString(jp.getArgs());
                String executeTime = String.valueOf(elapseTime);
                String returnType = String.valueOf(result);
                StringBuilder msg = new StringBuilder();
                log.info(msg.append(callMethod).append("|").append(args).append("|").append(returnType).append("|").append(executeTime).append("|").append(exception).toString());
            } catch (Exception e) {
                log.error("error in aop web log ", e);
            }
        }


    }

}
