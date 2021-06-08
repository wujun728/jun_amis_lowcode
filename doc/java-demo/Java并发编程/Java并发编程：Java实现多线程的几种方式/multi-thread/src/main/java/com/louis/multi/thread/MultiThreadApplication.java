package com.louis.multi.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.louis.multi.thread"})
public class MultiThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiThreadApplication.class, args);
	}

}
