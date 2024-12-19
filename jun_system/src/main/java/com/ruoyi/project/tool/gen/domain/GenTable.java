package com.ruoyi.project.tool.gen.domain;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.utils.StrUtils;
import com.ruoyi.framework.aspectj.lang.annotation.MapRow;
import com.ruoyi.framework.aspectj.lang.enums.RowType;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 业务表 gen_table
 * @author ruoyi
 */
public class GenTable extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 编号 */
	@MapRow(column = "table_id", type = RowType.LONG)
	private Long tableId;

	/** 表名称 */
	@NotBlank(message = "表名称不能为空")
	@MapRow(column = "table_name", type = RowType.STRING)
	private String tableName;

	/** 表描述 */
	@NotBlank(message = "表描述不能为空")
	@MapRow(column = "table_comment", type = RowType.STRING)
	private String tableComment;

	/** 实体类名称(首字母大写) */
	@NotBlank(message = "实体类名称不能为空")
	@MapRow(column = "class_name", type = RowType.STRING)
	private String className;

	/** 使用的模板（crud单表操作 tree树表操作） */
	@MapRow(column = "tpl_category", type = RowType.STRING)
	private String tplCategory;

	/** 生成包路径 */
	@NotBlank(message = "生成包路径不能为空")
	@MapRow(column = "package_name", type = RowType.STRING)
	private String packageName;

	/** 生成模块名 */
	@NotBlank(message = "生成模块名不能为空")
	@MapRow(column = "module_name", type = RowType.STRING)
	private String moduleName;

	/** 生成业务名 */
	@NotBlank(message = "生成业务名不能为空")
	@MapRow(column = "business_name", type = RowType.STRING)
	private String businessName;

	/** 生成功能名 */
	@NotBlank(message = "生成功能名不能为空")
	@MapRow(column = "function_name", type = RowType.STRING)
	private String functionName;

	/** 生成作者 */
	@NotBlank(message = "作者不能为空")
	@MapRow(column = "function_author", type = RowType.STRING)
	private String functionAuthor;

    /** 生成代码方式（0zip压缩包 1自定义路径） */
    @MapRow(column = "gen_type", type = RowType.STRING)
    private String genType;

    /** 生成路径（不填默认项目路径） */
    @MapRow(column = "gen_path", type = RowType.STRING)
    private String genPath;

	/** 其它生成选项 */
	@MapRow(column = "options", type = RowType.STRING)
	private String options;

	/** 主键信息 */
	private GenTableColumn pkColumn;

	/** 表列信息 */
	@Valid
	private List<GenTableColumn> columns;

	/** 树编码字段 */
	private String treeCode;

	/** 树父编码字段 */
	private String treeParentCode;

	/** 树名称字段 */
	private String treeName;

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTplCategory() {
		return tplCategory;
	}

	public void setTplCategory(String tplCategory) {
		this.tplCategory = tplCategory;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionAuthor() {
		return functionAuthor;
	}

	public void setFunctionAuthor(String functionAuthor) {
		this.functionAuthor = functionAuthor;
	}

    public String getGenType() {
        return genType;
    }

    public void setGenType(String genType) {
        this.genType = genType;
    }

    public String getGenPath() {
        return genPath;
    }

    public void setGenPath(String genPath) {
        this.genPath = genPath;
    }

	public GenTableColumn getPkColumn() {
		return pkColumn;
	}

	public void setPkColumn(GenTableColumn pkColumn) {
		this.pkColumn = pkColumn;
	}

	public List<GenTableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<GenTableColumn> columns) {
		this.columns = columns;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public String getTreeParentCode() {
		return treeParentCode;
	}

	public void setTreeParentCode(String treeParentCode) {
		this.treeParentCode = treeParentCode;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public boolean isTree() {
		return isTree(this.tplCategory);
	}

	public static boolean isTree(String tplCategory) {
		return tplCategory != null && StrUtils.equals(GenConstants.TPL_TREE, tplCategory);
	}

	public boolean isCrud() {
		return isCrud(this.tplCategory);
	}

	public static boolean isCrud(String tplCategory) {
		return tplCategory != null && StrUtils.equals(GenConstants.TPL_CRUD, tplCategory);
	}

	public boolean isSuperColumn(String javaField) {
		return isSuperColumn(this.tplCategory, javaField);
	}

	public static boolean isSuperColumn(String tplCategory, String javaField) {
		if (isTree(tplCategory)) {
			StrUtils.equalsAnyIgnoreCase(javaField, GenConstants.TREE_ENTITY);
		}
		return StrUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
	}
}