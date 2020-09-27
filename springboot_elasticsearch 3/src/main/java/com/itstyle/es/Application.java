package com.itstyle.es;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 启动类
 * 创建者  小柒2012
 * 网址 https://blog.52itstyle.com
 * 创建时间	2018年1月22日
 * linux 下 后台启动  nohup java -jar acts_elasticsearch.jar --server.port=8886 & 
 */
@SpringBootApplication
//不想使用可以注释掉
@ImportResource({"classpath:spring-context-dubbo.xml"})
public class Application extends WebMvcConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		logger.info("全文搜索服务启动 ");
	}
}