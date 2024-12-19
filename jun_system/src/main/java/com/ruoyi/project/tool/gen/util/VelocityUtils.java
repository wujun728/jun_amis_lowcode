package com.ruoyi.project.tool.gen.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import org.apache.velocity.VelocityContext;

import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.utils.StrUtils;
import com.ruoyi.project.tool.gen.domain.GenTable;
import com.ruoyi.project.tool.gen.domain.GenTableColumn;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class VelocityUtils {

	/** 项目空间路径 */
	private static final String PROJECT_PATH = "main/java";

	/** html空间路径 */
	private static final String TEMPLATES_PATH = "main/resources/templates";

	/**
	 * 设置模板变量信息
	 * @return 模板列表
	 */
	public static VelocityContext prepareContext(GenTable genTable) {
		String moduleName = genTable.getModuleName();
		String businessName = genTable.getBusinessName();
		String packageName = genTable.getPackageName();
		String tplCategory = genTable.getTplCategory();
		String functionName = genTable.getFunctionName();

		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("tplCategory", genTable.getTplCategory());//使用的模板（crud单表操作 tree树表操作）
		velocityContext.put("tableName", genTable.getTableName());          //表名称
		velocityContext.put("functionName", StrUtil.isNotBlank(functionName) ? functionName : "【请填写功能名称】");//生成功能名
		velocityContext.put("ClassName", genTable.getClassName());          //实体类名称
		velocityContext.put("className", StrUtils.uncapitalize(genTable.getClassName()));//实体类名称
		velocityContext.put("moduleName", genTable.getModuleName());        //模块名
		velocityContext.put("businessName", genTable.getBusinessName());    //业务名
		velocityContext.put("basePackage", getPackagePrefix(packageName));  //包前缀名称
		velocityContext.put("packageName", packageName);                    //包路径
		velocityContext.put("author", genTable.getFunctionAuthor());        //作者
		velocityContext.put("datetime", DateUtil.today());                  //当天日期
		velocityContext.put("pkColumn", genTable.getPkColumn());
		velocityContext.put("importList", getImportList(genTable.getColumns()));
		velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));//Controller映射前缀：模块名+业务名
		velocityContext.put("columns", genTable.getColumns());
		velocityContext.put("table", genTable);
		if (GenConstants.TPL_TREE.equals(tplCategory)) {
			setTreeVelocityContext(velocityContext, genTable);
		}
		return velocityContext;
	}

	public static void setTreeVelocityContext(VelocityContext context, GenTable genTable) {
		String options = genTable.getOptions();
		JSONObject paramsObj = JSONUtil.parseObj(options);
		String treeCode = getTreecode(paramsObj);
		String treeParentCode = getTreeParentCode(paramsObj);
		String treeName = getTreeName(paramsObj);

		context.put("treeCode", treeCode);
		context.put("treeParentCode", treeParentCode);
		context.put("treeName", treeName);
		context.put("expandColumn", getExpandColumn(genTable));
		if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
			context.put("tree_parent_code", paramsObj.getStr(GenConstants.TREE_PARENT_CODE));
		}
		if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
			context.put("tree_name", paramsObj.getStr(GenConstants.TREE_NAME));
		}
	}

	/**
	 * 获取模板信息
	 * @return 模板列表
	 */
	public static List<String> getTemplateList(String tplCategory) {
		List<String> templates = new ArrayList<String>();
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/java/service.java.vm");
		if (GenConstants.TPL_CRUD.equals(tplCategory)) {
			templates.add("vm/html/list.html.vm");
		} else if (GenConstants.TPL_TREE.equals(tplCategory)) {
			templates.add("vm/html/tree.html.vm");
			templates.add("vm/html/list-tree.html.vm");
		}
		templates.add("vm/html/add.html.vm");
		templates.add("vm/html/edit.html.vm");
		templates.add("vm/sql/sql.vm");
		return templates;
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, GenTable genTable) {
		// 文件名称
		String fileName = "";
		// 包路径
		String packageName = genTable.getPackageName();
		// 模块名
		String moduleName = genTable.getModuleName();
		// 大写类名
		String className = genTable.getClassName();
		// 业务名称
		String businessName = genTable.getBusinessName();

		String javaPath = PROJECT_PATH + "/" + StrUtils.replace(packageName, ".", "/");
		String htmlPath = TEMPLATES_PATH + "/" + moduleName + "/" + businessName;

		if (template.contains("service.java.vm")) {
			fileName = StrFormatter.format("{}/{}/service/{}Service.java", javaPath, businessName, className);
		} else if (template.contains("controller.java.vm")) {
			fileName = StrFormatter.format("{}/{}/controller/{}Controller.java", javaPath, businessName, className);
		} else if (template.contains("list.html.vm")) {
			fileName = StrFormatter.format("{}/{}.html", htmlPath, businessName);
		} else if (template.contains("list-tree.html.vm")) {
			fileName = StrFormatter.format("{}/{}.html", htmlPath, businessName);
		} else if (template.contains("tree.html.vm")) {
			fileName = StrFormatter.format("{}/tree.html", htmlPath);
		} else if (template.contains("add.html.vm")) {
			fileName = StrFormatter.format("{}/add.html", htmlPath);
		} else if (template.contains("edit.html.vm")) {
			fileName = StrFormatter.format("{}/edit.html", htmlPath);
		} else if (template.contains("sql.vm")) {
			fileName = businessName + "Menu.sql";
		}
		return fileName;
	}

	/**
	 * 获取包前缀
	 * @param packageName 包名称
	 * @return 包前缀名称
	 */
	public static String getPackagePrefix(String packageName) {
		int lastIndex = packageName.lastIndexOf(".");
		String basePackage = StrUtils.substring(packageName, 0, lastIndex);
		return basePackage;
	}

	/**
	 * 根据列类型获取导入包
	 * @param columns 列集合
	 * @return 返回需要导入的包列表
	 */
	public static HashSet<String> getImportList(List<GenTableColumn> columns) {
		HashSet<String> importList = new HashSet<String>();
        for (GenTableColumn column : columns) {
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.util.Date");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            } else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
	}

	/**
	 * 获取权限前缀
	 * @param moduleName 模块名称
	 * @param businessName 业务名称
	 * @return 返回权限前缀
	 */
	public static String getPermissionPrefix(String moduleName, String businessName) {
		return StrFormatter.format("{}:{}", moduleName, businessName);

	}

	/**
	 * 获取树编码
	 * @param paramsObj 生成其他选项
	 * @return 树编码
	 */
	public static String getTreecode(JSONObject paramsObj) {
		if (paramsObj.containsKey(GenConstants.TREE_CODE)) {
			return StrUtils.toCamelCase(paramsObj.getStr(GenConstants.TREE_CODE));
		}
		return "";
	}

	/**
	 * 获取树父编码
	 * @param paramsObj 生成其他选项
	 * @return 树父编码
	 */
	public static String getTreeParentCode(JSONObject paramsObj) {
		if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
			return StrUtils.toCamelCase(paramsObj.getStr(GenConstants.TREE_PARENT_CODE));
		}
		return "";
	}

	/**
	 * 获取树名称
	 * @param paramsObj 生成其他选项
	 * @return 树名称
	 */
	public static String getTreeName(JSONObject paramsObj) {
		if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
			return StrUtils.toCamelCase(paramsObj.getStr(GenConstants.TREE_NAME));
		}
		return "";
	}

	/**
	 * 获取需要在哪一列上面显示展开按钮
	 * @param genTable 业务表对象
	 * @return 展开按钮列序号
	 */
	public static int getExpandColumn(GenTable genTable) {
		String options = genTable.getOptions();
		JSONObject paramsObj = JSONUtil.parseObj(options);
		String treeName = paramsObj.getStr(GenConstants.TREE_NAME);
		int num = 0;
		for (GenTableColumn column : genTable.getColumns()) {
			if (column.isList()) {
				num++;
				String columnName = column.getColumnName();
				if (columnName.equals(treeName)) {
					break;
				}
			}
		}
		return num;
	}
}