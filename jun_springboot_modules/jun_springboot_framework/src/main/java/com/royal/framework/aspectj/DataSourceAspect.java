package com.royal.framework.aspectj;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.royal.common.enums.DataSourceType;
import com.royal.framework.datasource.DynamicDataSourceContextHolder;


/**
 * 多数据源处理
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect
{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.royal..*ServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        // 获取到当前执行的方法名
        String methodName = point.getSignature().getName();
        if (isSlave(methodName))
        {
            // 标记为读库,可以自定义选择数据源
            DynamicDataSourceContextHolder.setDateSoureType(DataSourceType.SLAVE.name());
        }
        else
        {
            // 标记为写库
            DynamicDataSourceContextHolder.setDateSoureType(DataSourceType.MASTER.name());
        }
        try
        {
            return point.proceed();
        }
        finally
        {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDateSoureType();
        }
    }

    /**
     * 判断是否为读库
     *
     * @param methodName
     * @return
     */
    private boolean isSlave(String methodName)
    {
        // 方法名以query、find、get开头的方法名走从库
        return StringUtils.startsWithAny(methodName, new String[]{"query", "find", "get", "select"});
    }
}
