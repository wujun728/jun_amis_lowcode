package com.jqp.admin.common.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

/**
 * Xcloud-Api By IDEA
 * Created by LaoWang on 2018/8/25.
 */
@Service
public class LogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        String exception = "";
        IThrowableProxy iThrowableProxy1 = event.getThrowableProxy();
        if(iThrowableProxy1!=null){
                exception = "<span class='excehtext'>"+iThrowableProxy1.getClassName()+" "+iThrowableProxy1.getMessage()+"</span></br>";
            for(int i=0; i<iThrowableProxy1.getStackTraceElementProxyArray().length;i++){
                exception += "<span class='excetext'>"+iThrowableProxy1.getStackTraceElementProxyArray()[i].toString()+"</span></br>";
            }
        }
        LoggerMessage loggerMessage = new LoggerMessage(
                StrUtil.format(event.getMessage(),event.getArgumentArray()).replaceAll("\n","<br/>").replaceAll("\t","&nbsp;&nbsp;&nbsp;&nbsp;")
                , DateUtil.format(new Date(event.getTimeStamp()),"yyyy-MM-dd HH:mm:ss SSS"),
                event.getThreadName(),
                event.getLoggerName(),
                event.getLevel().levelStr,
                exception,
                ""
        );
        LoggerQueue.push(loggerMessage);
        return FilterReply.ACCEPT;
    }
}
