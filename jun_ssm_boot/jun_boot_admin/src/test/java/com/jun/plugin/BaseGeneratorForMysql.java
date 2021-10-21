package com.jun.plugin;

import java.util.Scanner;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.jun.plugin.common.util.StringUtils;

/**
 * 代码生成器
 */
public class BaseGeneratorForMysql {
	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String outputFilePath = System.getProperty("user.dir") + "/src/main/resources/generator/";
		System.out.println(outputFilePath);
		gc.setOutputDir(outputFilePath);
		gc.setFileOverride(true); // 新文件覆盖旧文件
		gc.setActiveRecord(false);// 开启 activeRecord 模式
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap 通用查询结果集
		gc.setBaseColumnList(false);// XML columList 通用查询结果列
		gc.setAuthor("lch");
		gc.setOpen(false);
		// gc.setSwagger2(true); 实体属性 Swagger2 注解
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			@Override
			public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
				System.out.println("转换类型：" + fieldType);
				if (fieldType.toLowerCase().contains("datetime")) {
					return DbColumnType.DATE;
				}
				if (fieldType.toLowerCase().contains("tinyint")) {
					return DbColumnType.INTEGER;
				}
				return super.processTypeConvert(globalConfig, fieldType);
			}
		});
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/db_boot?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(scanner("模块名"));
		pc.setParent("com.jun.plugin");
		pc.setController("controller");
		pc.setEntity("model");
		pc.setService("service");
		pc.setMapper("mapper");
		pc.setXml("mybatis");
		mpg.setPackageInfo(pc);

		// 自定义配置
		/*
		 * InjectionConfig cfg = new InjectionConfig() {
		 * 
		 * @Override public void initMap() { Map<String, Object> map = new
		 * HashMap<String, Object>(); map.put("abc",
		 * this.getConfig().getGlobalConfig().getAuthor() + "-mp"); this.setMap(map); }
		 * }; List<FileOutConfig> focList = new ArrayList<>(); // 自定义配置会被优先输出
		 * focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
		 * 
		 * @Override public String outputFile(TableInfo tableInfo) { // 自定义输出文件名 ， 如果你
		 * Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！ return outputFilePath + "/mapper/" +
		 * pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" +
		 * StringPool.DOT_XML; } }); cfg.setFileOutConfigList(focList); mpg.setCfg(cfg);
		 */

		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		// TemplateConfig templateConfig = new TemplateConfig();
		// templateConfig.setEntity("templates/entity2.java");
		// templateConfig.setService();
		// templateConfig.setController();
		// templateConfig.setXml();
		// mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityTableFieldAnnotationEnable(true);
		// strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(false);
		strategy.setRestControllerStyle(false);
		// 公共父类
		// strategy.setSuperControllerClass("com.jun.plugin.modules.base.BaseController");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
		strategy.setControllerMappingHyphenStyle(true);
		// strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
