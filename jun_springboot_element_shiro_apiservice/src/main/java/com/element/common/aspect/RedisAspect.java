package com.element.common.aspect;

import com.element.common.exception.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Redis切面处理类
 */
@Aspect
@Configuration
public class RedisAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 是否开启redis缓存
    @Value("${spring.redis.open: false}")
    private boolean open;

    @Around("execution(* com.element.common.utils.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if (open) {
            try {
                result = point.proceed();
            } catch (Exception e) {
                logger.error("redis error", e);
                throw new CustomException("Redis服务异常");
            }
        }
        return result;
    }
}
