package com.ruoyi.project.system.dict.controller;

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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.Ztree;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.dict.service.DictTypeService;

/**
 * 数据字典信息
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dict")
public class DictTypeController extends BaseController {
    private String prefix = "system/dict";

	@Autowired
	private DictTypeService dictTypeService;

	@RequiresPermissions("system:dict:view")
	@GetMapping()
	public String dictType() {
		return prefix + "/dict";
	}

	@PostMapping("/list")
	@RequiresPermissions("system:dict:list")
	@ResponseBody
    public TableDataInfo list(HttpServletRequest request) {
        return dictTypeService.selectDictTypeList(request, true);
    }

	/**
	 * 新增字典类型
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 修改字典类型
	 */
	@GetMapping("/edit/{dict_type}")
	public String edit(@PathVariable("dict_type") String dict_type, ModelMap mmap) {
		mmap.put("dict", dictTypeService.selectDictTypeByType(dict_type));
		mmap.put("itemList", dictTypeService.selectDictItemList(dict_type));
		return prefix + "/edit";
	}

	/**
	 * 新增保存字典类型
	 */
	@Log(title = "字典类型", businessType = BusinessType.INSERT)
	@RequiresPermissions("system:dict:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HttpServletRequest request) {
		String dict_name = RequestUtil.getValue(request, "dict_name");
		if (dictTypeService.checkDictTypeUnique(request) > 0) {
			return error("新增字典'" + dict_name + "'失败，字典类型已存在！");
		}
		return toAjax(dictTypeService.insertDictType(request));
	}

	/**
	 * 修改保存字典类型
	 */
	@Log(title = "字典类型", businessType = BusinessType.UPDATE)
	@RequiresPermissions("system:dict:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HttpServletRequest request) {
		String dict_name = RequestUtil.getValue(request, "dict_name");
		if (dictTypeService.checkDictTypeUnique(request) > 0) {
			return error("修改字典'" + dict_name + "'失败，字典类型已存在！");
		}
		return toAjax(dictTypeService.updateDictType(request));
	}

	@Log(title = "字典类型", businessType = BusinessType.DELETE)
	@RequiresPermissions("system:dict:remove")
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(dictTypeService.deleteDictTypeByIds(ids));
	}

    /**
     * 清空缓存
     */
    @RequiresPermissions("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @GetMapping("/clearCache")
    @ResponseBody
    public AjaxResult clearCache() {
        dictTypeService.clearCache();
        return success();
    }

	/**
	 * 校验字典类型
	 */
	@PostMapping("/checkDictTypeUnique")
	@ResponseBody
	public int checkDictTypeUnique(HttpServletRequest request) {
		return dictTypeService.checkDictTypeUnique(request);
	}

	/**
	 * 选择字典树
	 */
	@GetMapping("/selectDictTree/{columnId}/{dict_type}")
	public String selectDeptTree(@PathVariable("columnId") Long columnId, @PathVariable("dict_type") String dict_type,
			ModelMap mmap) {
		mmap.put("columnId", columnId);
		mmap.put("dict", dictTypeService.selectDictTypeByType(dict_type));
		return prefix + "/tree";
	}

	/**
	 * 加载字典列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Ztree> treeData() {
		return dictTypeService.selectDictTree();
	}
}