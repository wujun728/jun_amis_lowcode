package com.zhonghe.active4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

/**
 * 入口启动类
 * @author teli_
 *
 */
@SpringBootApplication(scanBasePackages = {"org.jeecg.modules.jmreport", "com.zhonghe.active4j"})
@EnableScheduling //开启定时任务注解
@Slf4j
public class Active4jbootApplication {

	
	public static void main(String[] args) {
		log.info("============服务开始启动=============");
		SpringApplication.run(Active4jbootApplication.class, args);
		log.info("============服务启动成功=============");
		
	}

}

