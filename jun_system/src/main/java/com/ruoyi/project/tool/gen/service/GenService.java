package com.ruoyi.project.tool.gen.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.text.CharsetKit;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.db.BatchSql;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.tool.gen.domain.GenTable;
import com.ruoyi.project.tool.gen.domain.GenTableColumn;
import com.ruoyi.project.tool.gen.util.GenUtils;
import com.ruoyi.project.tool.gen.util.VelocityInitializer;
import com.ruoyi.project.tool.gen.util.VelocityUtils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 业务 服务层实现
 * @author ruoyi
 */
@Service
public class GenService extends CommonService {
	private static final Logger log = LoggerFactory.getLogger(GenService.class);

	/**
	 * 查询业务信息
	 * @param table_id 业务表ID
	 * @return 业务信息
	 */
	public GenTable selectGenTableById(String table_id) {
		String sql = "select t.table_id, t.table_name, t.table_comment, t.class_name, t.tpl_category, t.package_name, t.module_name, "+
					 "		 t.business_name, t.function_name, t.function_author, t.gen_type, t.gen_path, t.options, t.remark "+
					 "  from gen_table t where t.table_id = ?";
		GenTable genTable = db.queryForObject(sql, new Object[]{table_id}, GenTable.class);

		sql = "select c.table_id, c.column_id, c.column_name, c.column_comment, c.column_type, c.java_type, c.java_field, " +
			  "		  c.is_pk, c.is_increment, c.is_required, c.is_insert, c.is_edit, c.is_list, c.is_query, c.is_export, c.query_type, " +
			  "		  c.html_type, c.dict_type, c.sort, c.create_by, c.create_time, c.update_by, c.update_time " +
			  "	from gen_table_column c " +
			  " where c.table_id = ? order by c.sort";
		List<GenTableColumn> columns = db.queryForList(sql, new Object[]{table_id}, GenTableColumn.class);
		genTable.setColumns(columns);
		//设置代码生成其他选项值
		setTableFromOptions(genTable);
		return genTable;
	}

	/**
     * 查询业务字段列表
     * @param table_id 业务表ID
     * @return 业务字段集合
     */
	public List<GenTableColumn> selectGenTableColumnListByTableId(String table_id) {
		String sql = "select c.table_id, c.column_id, c.column_name, c.column_comment, c.column_type, c.java_type, c.java_field, " +
			  "		  c.is_pk, c.is_increment, c.is_required, c.is_insert, c.is_edit, c.is_list, c.is_query, c.query_type, " +
			  "		  c.html_type, c.dict_type, c.sort, c.create_by, c.create_time, c.update_by, c.update_time " +
			  "	from gen_table_column c " +
			  " where c.table_id = ? order by c.sort";
		return db.queryForList(sql, new Object[]{table_id}, GenTableColumn.class);
	}

	/**
	 * 查询业务列表
	 * @param request HttpServletRequest对象
	 * @return 业务集合
	 */
	public TableDataInfo selectGenTableList(HttpServletRequest request) {
		String table_name = RequestUtil.getValue(request, "table_name");
    	String table_comment = RequestUtil.getValue(request, "table_comment");
    	String start_time = RequestUtil.getValue(request, "start_time");
    	String end_time = RequestUtil.getValue(request, "end_time");

		StringBuffer sql = new StringBuffer();
    	sql.append("select table_id, table_name, table_comment, class_name, tpl_category, package_name, module_name, business_name, "+
				   "	   function_name, function_author, gen_type, gen_path, options, create_by, create_time, update_by, update_time, remark "+
				   "  from gen_table where 1 = 1 ");

    	List<String> paramList = new ArrayList<String>();
    	if(StrUtil.isNotBlank(table_name)) {
    		sql.append(" and table_name like concat('%', ?, '%') ");
    		paramList.add(table_name);
    	}
    	if(StrUtil.isNotBlank(table_comment)) {
    		sql.append(" and table_comment like concat('%', ?, '%') ");
    		paramList.add(table_comment);
    	}
    	if(StrUtil.isNotBlank(start_time)) {
    		sql.append(" and date_format(create_time,'%y%m%d') >= date_format(?,'%y%m%d') ");
    		paramList.add(start_time);
    	}
    	if(StrUtil.isNotBlank(end_time)) {
    		sql.append(" and date_format(create_time,'%y%m%d') <= date_format(?,'%y%m%d') ");
    		paramList.add(end_time);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "create_time desc");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, true);
	}

	/**
	 * 查询据库列表
	 * @param request HttpServletRequest对象
	 * @return 数据库表集合
	 */
	public TableDataInfo selectDbTableList(HttpServletRequest request) {
		String table_name = RequestUtil.getValue(request, "table_name");
    	String table_comment = RequestUtil.getValue(request, "table_comment");
    	String sortName = RequestUtil.getValue(request, "orderByColumn");
    	String isAsc = RequestUtil.getValue(request, "isAsc");

		StringBuffer sql = new StringBuffer();
    	sql.append("select table_name, table_comment, create_time, update_time " +
    			   "  from information_schema.tables" +
    			   " where table_schema = (select database())" +
    			   "   and table_name not like 'qrtz_%' and table_name not like 'gen_%'" +
    			   "   and table_name not in (select table_name from gen_table) ");

    	List<String> paramList = new ArrayList<String>();
    	if(StrUtil.isNotBlank(table_name)) {
    		sql.append(" and table_name like concat('%', ?, '%') ");
    		paramList.add(table_name);
    	}
    	if(StrUtil.isNotBlank(table_comment)) {
    		sql.append(" and table_comment like concat('%', ?, '%') ");
    		paramList.add(table_comment);
    	}
    	if(StrUtil.isNotBlank(sortName)) {
    		sql.append(" order by "+sortName+" "+isAsc);
    	}
    	else {
    		sql.append(" order by table_name");
    	}

        return this.getRespTableDataInfo(request, sql.toString(), paramList, true);
	}

	/**
	 * 查询据库列表
	 * @param tableNames 表名称组
	 * @return 数据库表集合
	 */
	public List<Map<String, Object>> selectDbTableListByNames(String[] tableNames) {
		String sql = "select table_name, table_comment, create_time, update_time " +
					 "  from information_schema.tables " +
					 " where table_name NOT LIKE 'qrtz_%' and table_name NOT LIKE 'gen_%' " +
					 "   and table_schema = (select database()) " +
					 "   and table_name in ("+SqlUtil.rebuildInSql(tableNames.length)+") " +
					 " order by table_name";
		return db.queryForList(sql, tableNames);
	}

    /**
     * 导入表结构
     * @param tables 导入表列表
     */
	public void importGenTable(String tables) {
		String[] tableNames = Convert.toStrArray(tables);
		String sql = "select table_name, table_comment, create_time, update_time " +
				 	 "  from information_schema.tables " +
				 	 " where table_name NOT LIKE 'qrtz_%' and table_name NOT LIKE 'gen_%' " +
				 	 "   and table_schema = (select database()) " +
				 	 "   and table_name in ("+SqlUtil.rebuildInSql(tableNames.length)+") " +
				 	 " order by table_name";
		List<GenTable> tableList = db.queryForList(sql, tableNames, GenTable.class);

		String operName = ShiroUtils.getLoginName();
        try {
            for (GenTable table : tableList) {
                String tableName = table.getTableName();
                GenUtils.initTable(table, operName);
                long tableId = this.insertGenTable(table);
                table.setTableId(tableId);
                // 保存列信息
                List<GenTableColumn> genTableColumns = selectDbTableColumnsByName(tableName);
                for (GenTableColumn column : genTableColumns) {
                    GenUtils.initColumnField(column, table);
                    insertGenTableColumn(column);
                }
            }
        } catch (Exception e) {
            throw new BusinessException("导入失败：" + e.getMessage());
        }
	}

	/**
	 * 新增业务字段
	 * @param column
	 * @return 结果
	 */
	public int insertGenTableColumn(GenTableColumn column) {
    	String operator_id = ShiroUtils.getLoginName();

		String sql = "insert into gen_table_column(table_id, column_name, column_comment, column_type, java_type, java_field, "+
					 "		 is_pk, is_increment, is_required, is_insert, is_edit, is_list, is_query, query_type, html_type, "+
					 "		 dict_type, sort, create_by, create_time) "+
					 "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate()) ";
		return db.execute(sql, new Object[]{column.getTableId(), column.getColumnName(), column.getColumnComment(),
				column.getColumnType(), column.getJavaType(), column.getJavaField(), column.getIsPk(), column.getIsIncrement(),
				column.getIsRequired(), column.getIsInsert(), column.getIsEdit(), column.getIsList(), column.getIsQuery(),
				column.getQueryType(), column.getHtmlType(), column.getDictType(), column.getSort(), operator_id});
	}

	/**
	 * 插入代码生成表
	 * @param table 业务表
	 * @return 结果
	 */
	public long insertGenTable(GenTable table) {
    	String operator_id = ShiroUtils.getLoginName();

		//插入代码生成表，返回自动增加的id号
		String sql = "insert into gen_table(table_name, table_comment, class_name, tpl_category, package_name, module_name, "+
					 "		 business_name, function_name, function_author, gen_type, gen_path, remark, create_by, create_time) "+
					 "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate()) ";
		return db.insert(sql, new String[]{table.getTableName(), table.getTableComment(), table.getClassName(),
				table.getTplCategory(), table.getPackageName(), table.getModuleName(), table.getBusinessName(),
				table.getFunctionName(), table.getFunctionAuthor(), table.getGenType(), table.getGenPath(), table.getRemark(), operator_id});
	}

	/**
	 * 根据表名称查询列信息
	 * @param tableName
	 * @return
	 */
	public List<GenTableColumn> selectDbTableColumnsByName(String tableName) {
		String sql = "select column_name, (case when (is_nullable = 'no' && column_key != 'PRI') then '1' else null end) as is_required, " +
					 "       (case when column_key = 'PRI' then '1' else '0' end) as is_pk, ordinal_position as sort, column_comment, " +
					 "       (case when extra = 'auto_increment' then '1' else '0' end) as is_increment, column_type " +
					 "  from information_schema.columns " +
					 " where table_schema = (select database()) and table_name = (?) " +
					 " order by ordinal_position";
		return db.queryForList(sql, new Object[]{tableName}, GenTableColumn.class);
	}

	/**
	 * 修改保存参数校验
	 * @param request HttpServletRequest对象
	 */
	public void validateEdit(HttpServletRequest request) {
    	String tpl_category = RequestUtil.getValue(request, "tpl_category");
    	String treeCode = RequestUtil.getValue(request, "treeCode");
    	String treeParentCode = RequestUtil.getValue(request, "treeParentCode");
    	String treeName = RequestUtil.getValue(request, "treeName");

		if (GenConstants.TPL_TREE.equals(tpl_category)) {
			if (StrUtil.isBlank(treeCode)) {
				throw new BusinessException("树编码字段不能为空");
			} else if (StrUtil.isBlank(treeParentCode)) {
				throw new BusinessException("树父编码字段不能为空");
			} else if (StrUtil.isBlank(treeName)) {
				throw new BusinessException("树名称字段不能为空");
			}
		}
	}

	/**
	 * 修改业务
	 * @param request HttpServletRequest对象
	 * @return 结果
	 */
	public void updateGenTable(HttpServletRequest request) {
		String table_id = RequestUtil.getValue(request, "table_id");
		String table_name = RequestUtil.getValue(request, "table_name");
		String table_comment = RequestUtil.getValue(request, "table_comment");
    	String class_name = RequestUtil.getValue(request, "class_name");
    	String function_author = RequestUtil.getValue(request, "function_author");
    	String tpl_category = RequestUtil.getValue(request, "tpl_category");
    	String package_name = RequestUtil.getValue(request, "package_name");
    	String module_name = RequestUtil.getValue(request, "module_name");
    	String business_name = RequestUtil.getValue(request, "business_name");
    	String function_name = RequestUtil.getValue(request, "function_name");
    	String gen_type = RequestUtil.getValue(request, "gen_type");
    	String gen_path = RequestUtil.getValue(request, "gen_path");
    	String remark = RequestUtil.getValue(request, "remark");
    	String treeCode = RequestUtil.getValue(request, "treeCode");
    	String treeParentCode = RequestUtil.getValue(request, "treeParentCode");
    	String treeName = RequestUtil.getValue(request, "treeName");

    	Map<String, Object> options = new HashMap<String, Object>();
    	options.put(GenConstants.TREE_CODE, treeCode);
    	options.put(GenConstants.TREE_PARENT_CODE, treeParentCode);
    	options.put(GenConstants.TREE_NAME, treeName);

    	String[] column_ids = RequestUtil.getValues(request, "column_id");
    	String[] column_comments = RequestUtil.getValues(request, "column_comment");
    	String[] java_types = RequestUtil.getValues(request, "java_type");
    	String[] java_fields = RequestUtil.getValues(request, "java_field");
    	String[] is_inserts = RequestUtil.getValues(request, "is_insert");
    	String[] is_edits = RequestUtil.getValues(request, "is_edit");
    	String[] is_lists = RequestUtil.getValues(request, "is_list");
    	String[] is_querys = RequestUtil.getValues(request, "is_query");
    	String[] is_exports = RequestUtil.getValues(request, "is_export");
    	String[] query_types = RequestUtil.getValues(request, "query_type");
    	String[] is_requireds = RequestUtil.getValues(request, "is_required");
    	String[] html_types = RequestUtil.getValues(request, "html_type");
    	String[] dict_types = RequestUtil.getValues(request, "dict_type");
    	String[] sorts = RequestUtil.getValues(request, "sort");

    	log.debug("gen_type = "+gen_type);
    	log.debug("gen_path = "+gen_path);

    	String operator_id = ShiroUtils.getLoginName();
		BatchSql batchSql = new BatchSql();
		//修改业务
		String sql = "update gen_table " +
					 "   set table_name = ?, table_comment = ?, class_name = ?, function_author = ?, tpl_category = ?, " +
					 "   	 package_name = ?, module_name = ?, business_name = ?, function_name = ?, " +
					 "   	 gen_type = ?, gen_path = ?, options = ?, remark = ?, update_by = ?, update_time = sysdate() " +
					 " where table_id = ?";
		batchSql.addBatch(sql, new Object[]{table_name, table_comment, class_name, function_author, tpl_category,
				package_name, module_name, business_name, function_name, gen_type, gen_path, JSONUtil.toJsonStr(options), remark, operator_id, table_id});

		sql = "update gen_table_column " +
			  "   set column_comment = ?, java_type = ?, java_field = ?, is_insert = ?, is_edit = ?, " +
			  "       is_list = ?, is_query = ?, is_export = ?, is_required = ?, query_type = ?, html_type = ?, " +
			  "       dict_type = ?, sort = ?, update_by = ?, update_time = sysdate() " +
			  " where column_id = ?";

		for(int i=0;i<column_ids.length;i++) {
    		batchSql.addBatch(sql, new Object[]{column_comments[i], java_types[i], java_fields[i], is_inserts[i], is_edits[i],
    				is_lists[i], is_querys[i], is_exports[i], is_requireds[i], query_types[i], html_types[i], dict_types[i],
    				sorts[i], operator_id, column_ids[i]});
		}

		db.doInTransaction(batchSql);
	}

	/**
	 * 删除业务对象
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public void deleteGenTableByIds(String ids) {
		String[] tableIds = Convert.toStrArray(ids);

        //根据参数个数创建相应数量的占位符
        String placeholders = SqlUtil.rebuildInSql(tableIds.length);

		BatchSql batchSql = new BatchSql();
    	String sql = "delete from gen_table where table_id in ("+placeholders+")";
    	batchSql.addBatch(sql, tableIds);

    	sql = "delete from gen_table_column where table_id in ("+placeholders+")";
    	batchSql.addBatch(sql, tableIds);

		db.doInTransaction(batchSql);
	}

	/**
	 * 预览代码
	 * @param tableId 表编号
	 * @return 预览数据列表
	 */
	public Map<String, String> previewCode(String tableId) {
		Map<String, String> dataMap = new LinkedHashMap<>();
		//查询表信息
		GenTable table = selectGenTableById(tableId);
		//查询列信息
		List<GenTableColumn> columns = table.getColumns();
		setPkColumn(table, columns);
		//初始化vm方法
		VelocityInitializer.initVelocity();

		//设置模板变量信息
		VelocityContext context = VelocityUtils.prepareContext(table);

		//获取模板列表
		List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
		for (String template : templates) {
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
		}
		return dataMap;
	}

	/**
	 * 生成代码（下载方式）
	 * @param table_id 表编号
	 * @return 数据
	 */
	public byte[] downloadCode(String table_id) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		generatorCode(table_id, zip);
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

    /**
     * 生成代码（自定义路径）
     * @param tableName 表名称
     * @return 数据
     */
    public void generatorCode(String table_id) {
        // 查询表信息
        GenTable table = this.selectGenTableById(table_id);
        // 查询列信息
        List<GenTableColumn> columns = table.getColumns();
        setPkColumn(table, columns);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            if (!StrUtil.contains(template, "sql.vm")) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, Constants.UTF8);
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(table, template);
                    FileUtils.writeStringToFile(new File(path), sw.toString(), CharsetKit.UTF_8);
                } catch (IOException e) {
                    throw new BusinessException("渲染模板失败，表名：" + table.getTableName());
                }
            }
        }
    }

    /**
     * 批量生成代码（下载方式）
     * @param tableIds 表数组
     * @return 数据
     */
    public byte[] downloadCode(String[] tableIds) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableId : tableIds) {
            generatorCode(tableId, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

	/**
	 * 查询表信息并生成代码
	 */
	private void generatorCode(String table_id, ZipOutputStream zip) {
		// 查询表信息
		GenTable table = this.selectGenTableById(table_id);
		// 查询列信息
		List<GenTableColumn> columns = table.getColumns();
		setPkColumn(table, columns);

		VelocityInitializer.initVelocity();

		VelocityContext context = VelocityUtils.prepareContext(table);

		// 获取模板列表
		List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory());
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
			try {
				// 添加到zip
				zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
				IOUtils.write(sw.toString(), zip, Constants.UTF8);
				IOUtils.closeQuietly(sw);
				zip.flush();
				zip.closeEntry();
			} catch (IOException e) {
				log.error("渲染模板失败，表名：" + table.getTableName(), e);
			}
		}
	}

	/**
	 * 设置主键列信息
	 * @param table 业务表信息
	 * @param columns 业务字段列表
	 */
	public void setPkColumn(GenTable table, List<GenTableColumn> columns) {
		for (GenTableColumn column : columns) {
			if (column.isPk()) {
				table.setPkColumn(column);
				break;
			}
		}
		if (ObjectUtil.isNull(table.getPkColumn())) {
			table.setPkColumn(columns.get(0));
		}
	}

	/**
	 * 设置代码生成其他选项值
	 * @param genTable 设置后的生成对象
	 */
	public void setTableFromOptions(GenTable genTable) {
		JSONObject paramsObj = JSONUtil.parseObj(genTable.getOptions());
		if (ObjectUtil.isNotNull(paramsObj)) {
			String treeCode = paramsObj.getStr(GenConstants.TREE_CODE);
			String treeParentCode = paramsObj.getStr(GenConstants.TREE_PARENT_CODE);
			String treeName = paramsObj.getStr(GenConstants.TREE_NAME);
			genTable.setTreeCode(treeCode);
			genTable.setTreeParentCode(treeParentCode);
			genTable.setTreeName(treeName);
		}
	}

    /**
     * 获取代码生成地址
     * @param table 业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(GenTable table, String template) {
        String genPath = table.getGenPath();
        if (StrUtil.equals(genPath, "/")) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(template, table);
    }

    /**
     * 同步数据库
     * @param tableId 表编号
     */
    public void synchDb(String tableId) {
        GenTable table = this.selectGenTableById(tableId);
        List<GenTableColumn> tableColumns = table.getColumns();
        List<String> tableColumnNames = tableColumns.stream().map(GenTableColumn::getColumnName).collect(Collectors.toList());

        List<GenTableColumn> dbTableColumns = this.selectGenTableColumnListByTableId(tableId);
        if (CollUtil.isEmpty(dbTableColumns)) {
            throw new BusinessException("同步数据失败，原表结构不存在");
        }
        List<String> dbTableColumnNames = dbTableColumns.stream().map(GenTableColumn::getColumnName).collect(Collectors.toList());

        dbTableColumns.forEach(column -> {
            if (!tableColumnNames.contains(column.getColumnName())) {
                GenUtils.initColumnField(column, table);
                this.insertGenTableColumn(column);
            }
        });

        List<GenTableColumn> delColumns = tableColumns.stream().filter(column -> !dbTableColumnNames.contains(column.getColumnName())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(delColumns)) {
            BatchSql batchSql = new BatchSql();
            String sql = "delete from gen_table_column where column_id = ?";
            for(GenTableColumn column : delColumns) {
                batchSql.addBatch(sql, new Object[]{column.getColumnId()});
            }
            db.doInTransaction(batchSql);
        }
    }
}