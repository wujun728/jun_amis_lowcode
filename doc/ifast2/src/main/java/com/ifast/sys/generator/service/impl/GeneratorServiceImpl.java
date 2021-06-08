package com.ifast.sys.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ifast.common.utils.GenUtils;
import com.ifast.common.utils.SpringContextHolder;
import com.ifast.sys.generator.dao.GeneratorMapper;
import com.ifast.sys.generator.dao.MysqlGeneratorMapper;
import com.ifast.sys.generator.dao.PostgreSQLGeneratorMapper;
import com.ifast.sys.generator.service.GeneratorService;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年3月23日 | Aron</small>
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
	// @Autowired
	GeneratorMapper generatorMapper;

	@Value("${spring.datasource.driverClassName:com.mysql.jdbc.Driver}")
	private String driverClassName;

	@Autowired
	public GeneratorMapper setGeneratorMapper() {
		if (driverClassName.equals("com.mysql.jdbc.Driver")) {
			generatorMapper = SpringContextHolder.getBean(MysqlGeneratorMapper.class);
		} else {
			generatorMapper = SpringContextHolder.getBean(PostgreSQLGeneratorMapper.class);
		}
		return generatorMapper;
	}

	@Override
	public List<Map<String, Object>> list() {
		List<Map<String, Object>> list = generatorMapper.list();
		return list;
	}

	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream       zip          = new ZipOutputStream(outputStream);
		for (String tableName : tableNames) {
			// 查询表信息
			Map<String, String> table = generatorMapper.get(tableName);
			// 查询列信息
			List<Map<String, String>> columns = generatorMapper.listColumns(tableName);
			// 生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
