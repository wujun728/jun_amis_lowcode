package com.itstyle.es;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 启动类
 * 创建者  小柒2012
 * 网址 https://blog.52itstyle.vip
 * 创建时间	2018年1月22日
 * linux 下 后台启动  nohup java -jar spring-boot-elasticsearch.jar --server.port=8886 &
 *
 * Kafka 启动：.\bin\windows\zookeeper-server-start.bat  .\config\zookeeper.properties
 */
@SpringBootApplication
@EnableDubboConfiguration
public class Application extends WebMvcConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
		logger.info("全文搜索服务启动");
	}
}