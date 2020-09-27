package cn.kiwipeach.demo;

import cn.kiwipeach.demo.service.LogbackService;
import cn.kiwipeach.demo.utils.IocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class LogbackApplicationMain {

    private static final Logger logger = LoggerFactory.getLogger(LogbackApplicationMain.class);

    /**
     *  1.springboot中的spring-boot-starter-web已经默认的包含了logback的日志实现(spring-boot-starter-logging)
     *  2.logback-spring.xml是spring增强版配置文件，可对多profile进行日志级别切换，spring.profiles.active=prod
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LogbackApplicationMain.class, args);
        //获取IOC容器Bean
        LogbackService logbackService = IocUtil.getBean(LogbackService.class);
        logbackService.showLogLevel();
        applicationContext.close();
    }
}
