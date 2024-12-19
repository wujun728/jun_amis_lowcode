package com.ruoyi.project.system.param.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ruoyi.common.utils.poi.ExcelUtilx;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.param.service.ParamService;

/**
 * 参数配置控制类
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/param")
public class ParamController extends BaseController
{
    private String prefix = "system/param";

    @Autowired
    private ParamService paramService;

    @RequiresPermissions("system:param:view")
    @GetMapping()
    public String frame() {
        return prefix + "/param";
    }

    /**
     * 查询参数配置列表
     */
    @RequiresPermissions("system:param:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HttpServletRequest request) {
        return paramService.selectParamList(request, true);
    }

    /**
     * 导出参数配置
     * @param request
     * @return
     */
    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:param:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HttpServletRequest request) {

    	LinkedHashMap<String, String> headers = new LinkedHashMap<String, String>();
    	//定义表头
    	headers.put("config_id", "参数主键");
    	headers.put("config_name", "参数名称");
    	headers.put("config_key", "参数键名");
    	headers.put("config_value", "参数键值");
    	headers.put("config_type_name", "系统内置");
    	headers.put("create_time", "创建时间");
    	headers.put("remark", "备注");

		//数据集合
		List<?> dataList = paramService.selectParamList(request, false).getRows();

		return ExcelUtilx.exportExcel(headers, dataList, "参数数据");
    }

    /**
     * 新增参数配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 修改参数配置
     */
    @GetMapping("/edit/{config_id}")
    public String edit(@PathVariable("config_id") String config_id, ModelMap mmap) {
        mmap.put("map", paramService.selectParamById(config_id));
        return prefix + "/edit";
    }

    /**
     * 保存参数配置
     */
    @RequiresPermissions("system:param:save")
    @Log(title = "工作文件管理", businessType = BusinessType.SAVE)
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult saveOrUpdate(HttpServletRequest request) {
    	String config_name = RequestUtil.getValue(request, "config_name");
    	if(paramService.checkConfigKeyUnique(request) > 0) {
    		return error("新增参数【" + config_name + "】失败，参数键名已存在！");
    	}
    	return toAjax(paramService.saveParam(request));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:param:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(paramService.deleteParamById(ids));
    }

    /**
     * 清空缓存
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @GetMapping("/clearCache")
    @ResponseBody
    public AjaxResult clearCache() {
        paramService.clearCache();
        return success();
    }

    /**
     * 校验参数键名
     */
    @PostMapping("/checkConfigKeyUnique")
    @ResponseBody
    public int checkConfigKeyUnique(HttpServletRequest request) {
        return paramService.checkConfigKeyUnique(request);
    }
}