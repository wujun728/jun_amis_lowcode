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


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LogbackApplicationMain.class, args);
        //获取IOC容器Bean
        LogbackService logbackService = IocUtil.getBean(LogbackService.class);
        logbackService.showLogLevel();
        applicationContext.close();
    }
}
