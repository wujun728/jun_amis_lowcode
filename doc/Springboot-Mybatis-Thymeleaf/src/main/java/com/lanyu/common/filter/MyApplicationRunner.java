package com.lanyu.common.filter;

import com.lanyu.common.model.vo.ConfigPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.lang.reflect.Field;


/**
 * 服务器启动时执行，读取配置信息保存在servletContext
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private ConfigPath SystemConfig;
    @Autowired
    private ServletContext servletContext;
    @Override
    public void run(ApplicationArguments var1) throws Exception{
        Class cls = SystemConfig.getClass();
        Field[] fields = cls.getDeclaredFields();
        System.out.println("-------------------------------------");
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(SystemConfig));
            servletContext.setAttribute(f.getName(), f.get(SystemConfig));
        }
        System.out.println("-----------------------------------");
    }

}
