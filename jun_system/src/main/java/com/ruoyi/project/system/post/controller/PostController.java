package com.ruoyi.project.system.post.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.ruoyi.project.system.post.service.PostService;

/**
 * 岗位信息操作处理
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/post")
public class PostController extends BaseController {
    private String prefix = "system/post";

    @Autowired
    private PostService postService;

    @RequiresPermissions("system:post:view")
    @GetMapping()
	public String frame() {
		return prefix + "/post";
	}

    /**
     * 查询岗位列表
     */
    @RequiresPermissions("system:post:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HttpServletRequest request) {
        return postService.selectPostList(request, true);
    }

    /**
     * 导出岗位
     */
    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:post:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HttpServletRequest request) {
    	//定义表头
    	Map<String, String> headers = new LinkedHashMap<String, String>();
    	headers.put("post_id", "岗位编号");
    	headers.put("post_code", "岗位编码");
    	headers.put("post_name", "岗位名称");
    	headers.put("post_sort", "显示顺序");
    	headers.put("status_name", "状态");
    	headers.put("create_time", "创建时间");
    	headers.put("remark", "备注");

		//数据集合
		List<?> dataList = postService.selectPostList(request, false).getRows();

		return ExcelUtilx.exportExcel(headers, dataList, "岗位数据");
    }

    /**
     * 删除岗位
     */
    @RequiresPermissions("system:post:remove")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(postService.deletePostByIds(ids));
    }

    /**
     * 新增岗位
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 修改岗位
     */
    @GetMapping("/edit/{post_id}")
    public String edit(@PathVariable("post_id") String post_id, ModelMap mmap) {
        mmap.put("map", postService.selectPostById(post_id));
        return prefix + "/edit";
    }

    /**
     * 保存岗位
     */
    @RequiresPermissions("system:post:save")
    @Log(title = "岗位管理", businessType = BusinessType.SAVE)
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult saveOrUpdate(HttpServletRequest request) {
    	String post_name = RequestUtil.getValue(request, "post_name");
    	if(postService.checkPostNameUnique(request) > 0) {
    		return error("配置岗位【" + post_name + "】失败，岗位名称已存在");
    	}
    	if (postService.checkPostCodeUnique(request) > 0) {
            return error("配置岗位【" + post_name + "】失败，岗位编码已存在");
        }
    	return toAjax(postService.savePost(request));
    }

    /**
     * 校验岗位名称
     */
    @PostMapping("/checkPostNameUnique")
    @ResponseBody
    public int checkPostNameUnique(HttpServletRequest request) {
        return postService.checkPostNameUnique(request);
    }

    /**
     * 校验岗位编码
     */
    @PostMapping("/checkPostCodeUnique")
    @ResponseBody
    public int checkPostCodeUnique(HttpServletRequest request) {
        return postService.checkPostCodeUnique(request);
    }
}