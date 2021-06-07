package com.active4j.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时任务注解
public class Active4jhrApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Active4jhrApplication.class, args);
	}

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Active4jhrApplication.class);
	}
}
