package com.louis.spring.cloud.consul.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsuleProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsuleProducerApplication.class, args);
	}
}