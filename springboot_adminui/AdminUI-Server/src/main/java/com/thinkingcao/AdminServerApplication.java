package com.thinkingcao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * <pre>
 * @author Wujun
 * @date 2018年11月22日 上午10:36:11
 * </pre>
 */
@SpringBootApplication
@EnableAdminServer
@EnableAutoConfiguration
public class AdminServerApplication {

	/**
	 * <pre>  
	 * @author Wujun
	 * @param args
	 * </pre>  
	 */
	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}

}
