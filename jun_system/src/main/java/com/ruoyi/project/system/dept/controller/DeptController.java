package com.ruoyi.project.system.dept.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StrUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
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
import com.ruoyi.project.system.dept.service.DeptService;

/**
 * 部门信息
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
	private String prefix = "system/dept";

	@Autowired
	private DeptService deptService;

	@RequiresPermissions("system:dept:view")
	@GetMapping()
	public String dept() {
		return prefix + "/dept";
	}

	@RequiresPermissions("system:dept:list")
	@PostMapping("/list")
	@ResponseBody
	public List<Map<String, Object>> list(HttpServletRequest request) {
		return deptService.selectDeptList(request);
	}

	/**
	 * 新增部门
	 */
	@GetMapping("/add/{parent_id}")
	public String add(@PathVariable("parent_id") String parent_id, ModelMap mmap) {
        if (!ShiroUtils.getSysUser().isAdmin()) {
            parent_id = String.valueOf(ShiroUtils.getSysUser().getDeptId());
        }
		mmap.put("parentDept", deptService.selectDeptById(parent_id));
		return prefix + "/add";
	}

	/**
	 * 修改
	 */
	@GetMapping("/edit/{dept_id}")
	public String edit(@PathVariable("dept_id") String dept_id, ModelMap mmap) {
		mmap.put("map", deptService.selectDeptById(dept_id));
		return prefix + "/edit";
	}

    /**
     * 保存部门
     */
    @RequiresPermissions("system:dept:save")
    @Log(title = "部门管理", businessType = BusinessType.SAVE)
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult saveOrUpdate(HttpServletRequest request) {
    	String dept_name = RequestUtil.getValue(request, "dept_name");
    	String dept_id = RequestUtil.getValue(request, "dept_id");
    	String parent_id = RequestUtil.getValue(request, "parent_id");
        String status = RequestUtil.getValue(request, "status");

    	if(deptService.checkDeptNameUnique(request) > 0) {
			return error("新增部门'" + dept_name + "'失败，部门名称已存在！");
    	}
    	if (parent_id.equals(dept_id)) {
			return error("修改部门'" + dept_name + "'失败，上级部门不能是自己！");
        }
        else if (StrUtils.equals(UserConstants.DEPT_DISABLE, status) && deptService.selectNormalChildrenDeptById(dept_id) > 0) {
            return AjaxResult.error("该部门包含未停用的子部门！");
        }

    	return toAjax(deptService.saveDept(request));
    }

	/**
	 * 删除
	 */
	@Log(title = "部门管理", businessType = BusinessType.DELETE)
	@RequiresPermissions("system:dept:remove")
	@GetMapping("/remove/{dept_id}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("dept_id") String dept_id) {
		if (deptService.selectDeptCount(dept_id, null) > 0) {
			return AjaxResult.warn("存在下级部门，不允许删除！");
		}
		if (deptService.checkDeptExistUser(dept_id)) {
			return AjaxResult.warn("部门存在用户，不允许删除！");
		}
		return toAjax(deptService.deleteDeptById(dept_id));
	}

	/**
	 * 校验部门名称
	 */
	@PostMapping("/checkDeptNameUnique")
	@ResponseBody
	public int checkDeptNameUnique(HttpServletRequest request) {
		return deptService.checkDeptNameUnique(request);
	}

	/**
	 * 选择部门树
	 */
    @GetMapping(value = { "/selectDeptTree/{dept_id}", "/selectDeptTree/{dept_id}/{excludeId}" })
	public String selectDeptTree(@PathVariable("dept_id") String dept_id,
                                 @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap) {
		mmap.put("dept", deptService.selectDeptById(dept_id));
        mmap.put("excludeId", excludeId);
		return prefix + "/tree";
	}

	/**
	 * 加载部门列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Ztree> treeData(HttpServletRequest request) {
		List<Ztree> ztrees = deptService.selectDeptTree(request);
		return ztrees;
	}

    /**
     * 加载部门列表树（排除下级）
     */
    @GetMapping("/treeData/{excludeId}")
    @ResponseBody
    public List<Ztree> treeDataExcludeChild(@PathVariable(value = "excludeId", required = false) String excludeId) {
        List<Ztree> ztrees = deptService.selectDeptTreeExcludeChild(excludeId);
        return ztrees;
    }

	/**
	 * 加载角色部门（数据权限）列表树
	 */
	@GetMapping("/roleDeptTreeData")
	@ResponseBody
	public List<Ztree> deptTreeData(HttpServletRequest request) {
		List<Ztree> ztrees = deptService.roleDeptTreeData(request);
		return ztrees;
	}
}