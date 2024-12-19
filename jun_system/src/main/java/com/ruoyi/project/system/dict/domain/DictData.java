package com.ruoyi.project.system.dict.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.aspectj.lang.annotation.MapRow;
import com.ruoyi.framework.aspectj.lang.enums.RowType;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 字典数据表 sys_dict_data
 * @author ruoyi
 */
public class DictData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 字典编码 */
    @MapRow(column = "dict_code", type = RowType.LONG)
    private Long dictCode;

    /** 字典排序 */
    @MapRow(column = "dict_sort", type = RowType.LONG)
    private Long dictSort;

    /** 字典标签 */
    @MapRow(column = "dict_label", type = RowType.STRING)
    private String dictLabel;

    /** 字典键值 */
    @MapRow(column = "dict_value", type = RowType.STRING)
    private String dictValue;

    /** 字典类型 */
    @MapRow(column = "dict_type", type = RowType.STRING)
    private String dictType;

    /** 样式属性（其他样式扩展） */
    @MapRow(column = "css_class", type = RowType.STRING)
    private String cssClass;

    /** 表格字典样式 */
	@MapRow(column = "list_class", type = RowType.STRING)
    private String listClass;

    /** 是否默认（Y是 N否） */
    @MapRow(column = "is_default", type = RowType.STRING)
    private String isDefault;

    /** 状态（0正常 1停用） */
    @MapRow(column = "status", type = RowType.STRING)
    private String status;

	public Long getDictCode() {
		return dictCode;
	}

	public void setDictCode(Long dictCode) {
		this.dictCode = dictCode;
	}

	public Long getDictSort() {
		return dictSort;
	}

	public void setDictSort(Long dictSort) {
		this.dictSort = dictSort;
	}

	@NotBlank(message = "字典标签不能为空")
	@Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
	public String getDictLabel() {
		return dictLabel;
	}

	public void setDictLabel(String dictLabel) {
		this.dictLabel = dictLabel;
	}

	@NotBlank(message = "字典键值不能为空")
	@Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	@Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getListClass() {
		return listClass;
	}

	public void setListClass(String listClass) {
		this.listClass = listClass;
	}

	public boolean getDefault() {
		return UserConstants.YES.equals(this.isDefault) ? true : false;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("dictCode", getDictCode())
				.append("dictSort", getDictSort()).append("dictLabel", getDictLabel())
				.append("dictValue", getDictValue()).append("dictType", getDictType()).append("cssClass", getCssClass())
				.append("listClass", getListClass()).append("isDefault", getIsDefault()).append("status", getStatus())
				.append("createBy", getCreateBy()).append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).append("remark", getRemark())
				.toString();
	}
}