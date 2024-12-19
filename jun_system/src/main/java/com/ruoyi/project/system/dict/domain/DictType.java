package com.ruoyi.project.system.dict.domain;

import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.MapRow;
import com.ruoyi.framework.aspectj.lang.enums.RowType;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 字典类型表 sys_dict_type
 * @author ruoyi
 */
public class DictType extends BaseEntity {
    private static final long serialVersionUID = 1L;

	/** 字典主键 */
	@MapRow(column = "dict_id", type = RowType.LONG)
	private Long dictId;

	/** 字典名称 */
	@MapRow(column = "dict_name", type = RowType.STRING)
	private String dictName;

	/** 字典类型 */
	@MapRow(column = "dict_type", type = RowType.STRING)
	private String dictType;

	/** 状态（0正常 1停用） */
	@MapRow(column = "status", type = RowType.STRING)
	private String status;

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	@NotBlank(message = "字典名称不能为空")
	@Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("dictId", getDictId())
				.append("dictName", getDictName()).append("dictType", getDictType()).append("status", getStatus())
				.append("createBy", getCreateBy()).append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).append("remark", getRemark())
				.toString();
	}
}