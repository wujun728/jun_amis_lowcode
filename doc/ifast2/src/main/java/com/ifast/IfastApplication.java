package com.ifast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ifast.common.IFastProperties;
import com.ifast.common.utils.SpringContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * ifast 入口
 * </pre>
 * 
 * <small> 2018年3月22日 | Aron</small>
 */
@Slf4j

@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.ifast.**.dao")
@SpringBootApplication
public class IfastApplication {

	/**
	 * <pre>
	 * </pre>
	 * 
	 * <small> 2018年3月22日 | Aron</small>
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(IfastApplication.class, args);

		printProjectConfigs(ctx);
	}

	private static void printProjectConfigs(ApplicationContext ctx) {
		ServerProperties     serverProperties     = SpringContextHolder.getBean(ServerProperties.class);
		DataSourceProperties dataSourceProperties = SpringContextHolder.getBean(DataSourceProperties.class);
		IFastProperties      config               = SpringContextHolder.getBean(IFastProperties.class);
		log.info("开启演示模式：{}", config.isDemoMode());
		log.info("开启调试模式：{}", config.isDevMode());
		log.info("数据库：{}", dataSourceProperties.getUrl());
		log.info("====> run at http://localhost:{}  <====", serverProperties.getPort() + serverProperties.getServlet().getContextPath());
	}

}
