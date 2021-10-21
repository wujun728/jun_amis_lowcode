package com.jun.biz.common.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created on 2018/7/13 16:54
 * <p>
 * Description: []
 * <p>
 * Company: []
 *
 * 
 */
@Configuration
@PropertySource("classpath:/application-dao.properties")
@MapperScan("com.jun.biz.manager.dao")
public class MybatisConfiguration {
}
