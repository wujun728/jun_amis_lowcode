package com.htmall.entity.excel;

import org.apache.poi.ss.usermodel.FillPatternType;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.annotation.write.style.HeadStyle;

@HeadRowHeight(20)
@ContentRowHeight(11)
@ColumnWidth(25)

@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 22)
@HeadFontStyle(fontHeightInPoints = 18)
@ContentFontStyle(fontHeightInPoints = 10)
public class DeptExcel {

	/**
	 * 部门名称
	 */
	@ExcelProperty("部门名称")
	private String deptName;

	/**
	 * 描述
	 */
	@ColumnWidth(30)
	@ExcelProperty("部门描述")
	private String deptDesc;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

}
