package com.qingshop.mall;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MallApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MallApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}