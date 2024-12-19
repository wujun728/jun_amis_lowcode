package com.ruoyi.project.tool.gen.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.tool.gen.domain.GenTableColumn;
import com.ruoyi.project.tool.gen.service.GenService;

import cn.hutool.core.convert.Convert;

/**
 * 代码生成 操作处理
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/gen")
public class GenController extends BaseController {
    private String prefix = "tool/gen";

    @Autowired
    private GenService genService;

    @RequiresPermissions("tool:gen:view")
    @GetMapping()
	public String gen() {
		return prefix + "/gen";
	}

    /**
     * 查询代码生成列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/list")
    @ResponseBody
	public TableDataInfo genList(HttpServletRequest request) {
    	return genService.selectGenTableList(request);
	}

    /**
     * 查询数据库列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/db/list")
    @ResponseBody
    public TableDataInfo dataList(HttpServletRequest request) {
    	return genService.selectDbTableList(request);
    }

    /**
     * 查询数据表字段列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/column/list")
    @ResponseBody
	public TableDataInfo columnList(HttpServletRequest request) {
		String table_id = RequestUtil.getValue(request, "table_id");
		TableDataInfo dataInfo = new TableDataInfo();
		List<GenTableColumn> list = genService.selectGenTableColumnListByTableId(table_id);
		dataInfo.setRows(list);
		dataInfo.setTotal(list.size());
		return dataInfo;
	}

    /**
     * 导入表结构
     */
    @GetMapping("/importTable")
	public String importTable() {
		return prefix + "/importTable";
	}

    /**
     * 导入表结构（保存）
     */
    @RequiresPermissions("tool:gen:import")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    @ResponseBody
    public AjaxResult importTableSave(String tables) {
        genService.importGenTable(tables);
        return AjaxResult.success();
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping("/edit/{table_id}")
    public String edit(@PathVariable("table_id") String table_id, ModelMap mmap) {
        mmap.put("table", genService.selectGenTableById(table_id));
        return prefix + "/edit";
    }

    /**
     * 修改保存代码生成业务
     */
    @RequiresPermissions("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
	public AjaxResult editSave(HttpServletRequest request) {
		genService.validateEdit(request);
		genService.updateGenTable(request);
		return AjaxResult.success();
	}

    @RequiresPermissions("tool:gen:remove")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        genService.deleteGenTableByIds(ids);
        return AjaxResult.success();
    }

    /**
     * 预览代码
     */
    @RequiresPermissions("tool:gen:preview")
    @GetMapping("/preview/{table_id}")
    @ResponseBody
	public AjaxResult preview(@PathVariable("table_id") String table_id) throws IOException {
		Map<String, String> dataMap = genService.previewCode(table_id);
		return AjaxResult.success(dataMap);
	}

    /**
     * 生成代码（下载方式）
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{table_id}")
    public void download(HttpServletResponse response, @PathVariable("table_id") String table_id) throws IOException {
        byte[] data = genService.downloadCode(table_id);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{table_id}")
    @ResponseBody
    public AjaxResult genCode(@PathVariable("table_id") String table_id) {
        genService.generatorCode(table_id);
        return AjaxResult.success();
    }

    /**
     * 批量生成代码
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    @ResponseBody
	public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
		String[] tableIds = Convert.toStrArray(tables);
		byte[] data = genService.downloadCode(tableIds);
		genCode(response, data);
	}

    /**
     * 生成zip文件
     */
	private void genCode(HttpServletResponse response, byte[] data) throws IOException {
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
	}

    /**
     * 同步数据库
     */
    @RequiresPermissions("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/synchDb/{table_id}")
    @ResponseBody
    public AjaxResult synchDb(@PathVariable("table_id") String table_id) {
        genService.synchDb(table_id);
        return AjaxResult.success();
    }
}