package com.htmall.controller.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htmall.common.anno.Log;
import com.htmall.common.bean.Rest;
import com.htmall.common.controller.BaseController;
import com.htmall.entity.SysDept;
import com.htmall.entity.excel.DeptExcel;
import com.htmall.service.ISysDeptService;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {

	@Autowired
	private ISysDeptService sysDeptService;

	/**
	 * 分页查询部门
	 */
	@RequiresPermissions("listDept")
	@RequestMapping("/list/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue = "15") Integer pageSize,
			String search, Model model) {

		Page<SysDept> page = getPage(pageNumber, pageSize);

		// 查询分页
		QueryWrapper<SysDept> ew = new QueryWrapper<SysDept>();
		if (StringUtils.isNotBlank(search)) {
			ew.like("dept_name", search);
			model.addAttribute("search", search);
		}
		IPage<SysDept> pageData = sysDeptService.page(page, ew);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageData", pageData);
		return "system/dept/list";
	}

	/**
	 * 新增部门
	 */
	@RequiresPermissions("addDept")
	@RequestMapping("/add")
	public String add(Model model) {
		return "system/dept/add";
	}

	/**
	 * 执行新增
	 */
	@RequiresPermissions("addDept")
	@Log("创建部门")
	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(SysDept dept, String[] roleId) {
		sysDeptService.save(dept);
		return Rest.ok();
	}

	/**
	 * 删除部门
	 */
	@RequiresPermissions("deleteDept")
	@Log("删除部门")
	@RequestMapping("/delete")
	@ResponseBody
	public Rest delete(String id) {
		sysDeptService.removeById(id);
		return Rest.ok();
	}

	/**
	 * 编辑部门
	 */
	@RequiresPermissions("editDept")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		SysDept dept = sysDeptService.getById(id);
		model.addAttribute("dept", dept);
		return "system/dept/edit";
	}

	/**
	 * 执行编辑
	 */
	@RequiresPermissions("editDept")
	@Log("编辑部门")
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(SysDept dept, Model model) {
		sysDeptService.updateById(dept);
		return Rest.ok();
	}

	@RequestMapping("toImport")
	public String importTemplte() {
		return "system/dept/export";
	}
	
	/**
	 * 下载模板
	 * 
	 * @return
	 */
	@PostMapping("/importDeptTemplate")
	@ResponseBody
	public Rest importStudentTemplate() {
		OutputStream os = null; // 输出流
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook();
			// 生成一个标题样式
			HSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			// 生成一个字体
			HSSFFont font = workbook.createFont();
			font.setBold(true);
			font.setFontName("宋体");
			font.setColor(IndexedColors.WHITE.index);
			font.setFontHeightInPoints((short) 10);
			// 把字体应用到当前的样式
			style.setFont(font);
			// 生成一个内容样式
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 居中
			cellStyle.setWrapText(true);
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("Sheet1");
			sheet.setDefaultRowHeight((short) 600);
			// 部门名称
			sheet.setColumnWidth(0, 100 * 25);
			// 部门描述
			sheet.setColumnWidth(1, 100 * 30);
			// 设置列风格
			sheet.setDefaultColumnStyle(0, cellStyle);
			sheet.setDefaultColumnStyle(1, cellStyle);
			// 产生表格标题行
			HSSFRow row = sheet.createRow(0);
			row.setHeight((short) 600);
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellStyle(style);
			cell0.setCellValue("部门名称");
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue("部门描述");
			// 单选题样例
			HSSFRow row1 = sheet.createRow(1);
			row1.setHeight((short) 600);
			row1.createCell(0).setCellValue("总经办");
			row1.createCell(1).setCellValue("管理部门");
			// 设置返回文件信息
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("试题导入模板.xls", "UTF-8"));
			// 将内容使用字节流写入输出流中
			os = response.getOutputStream();
			workbook.write(os);
			return Rest.ok();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Rest.failure("下载模板失败");
		} catch (IOException e) {
			e.printStackTrace();
			return Rest.ok();
		} finally {
			// 关闭流信息
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/importDept", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Rest importDept(MultipartFile importFile, String extraData) {
		if (importFile == null) {
			return Rest.failure("文件上传失败");
		}
		System.out.println("额外参数" + extraData);
		sysDeptService.importDept(importFile, sysDeptService);
		return Rest.ok();
	}

	@RequestMapping(value = "/exportDept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Rest exportDept(String keyWord) {
		try {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setCharacterEncoding("utf-8");
			String fileName = URLEncoder.encode("部门数据", "UTF-8").replaceAll("\\+", "%20");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			Map<String, Object> queryParam = new HashMap<String, Object>();
			queryParam.put("keyWord", keyWord);
			List<DeptExcel> list = sysDeptService.listExportDepe(queryParam);
			EasyExcel.write(response.getOutputStream(), DeptExcel.class).sheet("部门数据").doWrite(list);
			return Rest.ok();
		} catch (Exception e) {
			return Rest.failure("文件导出失败");
		}
	}

}
