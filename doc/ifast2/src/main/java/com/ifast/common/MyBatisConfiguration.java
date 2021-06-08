package com.ifast.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

@Configuration
public class MyBatisConfiguration {

	//MybatisPlusProperties
	//MybatisSqlSessionFactoryBean
	//MybatisPlusAutoConfiguration
	// /**
	// * mybatis-plus SQL执行效率插件【生产环境可以关闭】
	// */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}

	@Bean
	public ISqlInjector iSqlInjector() {
		return new LogicSqlInjector();
	}

	/**
	 * 分页插件
	 * 
	 * @return
	 * @author zhongweiyuan
	 * @date 2018年4月14日下午4:13:15
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		// paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
		// /*
		// * 【测试多租户】 SQL 解析处理拦截器<br>
		// * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
		// */
		// List<ISqlParser> sqlParserList = new ArrayList<>();
		// TenantSqlParser tenantSqlParser = new TenantSqlParser();
		// tenantSqlParser.setTenantHandler(new TenantHandler() {
		// @Override
		// public Expression getTenantId() {
		// return new LongValue(1L);
		// }
		//
		// @Override
		// public String getTenantIdColumn() {
		// return "tenant_id";
		// }
		//
		// @Override
		// public boolean doTableFilter(String tableName) {
		// // 这里可以判断是否过滤表
		// /*
		// if ("user".equals(tableName)) {
		// return true;
		// }*/
		// return false;
		// }
		// });
		//
		//
		// sqlParserList.add(tenantSqlParser);
		// paginationInterceptor.setSqlParserList(sqlParserList);
		// paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
		// @Override
		// public boolean doFilter(MetaObject metaObject) {
		// MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
		// // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
		// if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
		// return true;
		// }
		// return false;
		// }
		// });
		return paginationInterceptor;
	}

	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}
}
