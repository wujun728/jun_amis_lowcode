package com.jun.plugin.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.util.Utils;
import com.jun.plugin.config.properties.DruidProperties;

@Configuration
public class DruidConfig {
	
	@Bean
	@ConfigurationProperties("spring.datasource.druid")
	public DataSource masterDataSource(DruidProperties druidProperties) {
		DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
		return druidProperties.dataSource(dataSource);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		return servletRegistrationBean;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	@ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true", matchIfMissing = true)
	public FilterRegistrationBean removeDruidAdFilterRegistrationBean(DruidStatProperties properties) {
		/**
		 * 获取监控页面参数
		 */
		DruidStatProperties.StatViewServlet druidConfig = properties.getStatViewServlet();
		/**
		 * 获取common.js位置
		 */
		String pattern = druidConfig.getUrlPattern() != null ? druidConfig.getUrlPattern() : "/druid/*";
		String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
		final String filePath = "support/http/resources/js/common.js";
		/**
		 * 利用Filter进行过滤
		 */
		Filter filter = new Filter() {
			@Override
			public void init(FilterConfig filterConfig) throws ServletException {
			}

			@Override
			public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
				chain.doFilter(request, response);
				response.resetBuffer();
				String text = Utils.readFromResource(filePath);
				text = text.replaceAll("<a.*?banner\"></a><br/>", "");
				text = text.replaceAll("powered.*?shrek.wang</a>", "");
				response.getWriter().write(text);
			}

			@Override
			public void destroy() {
			}
		};
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns(commonJsPattern);
		return registrationBean;
	}
}
