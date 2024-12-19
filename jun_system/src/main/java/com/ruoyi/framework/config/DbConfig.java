package com.ruoyi.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ruoyi.common.utils.db.DBUtils;

@Configuration
public class DbConfig {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Bean(name = "db")
	@DependsOn("jdbcTemplate")
	public DBUtils initDBUtils() {
		DBUtils db = new DBUtils(jdbcTemplate);
		return db;
	}
}
