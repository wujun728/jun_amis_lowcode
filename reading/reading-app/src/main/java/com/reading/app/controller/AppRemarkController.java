package com.reading.app.controller;

import com.reading.app.domain.ReRemark;
import com.reading.app.service.IReRemarkService;
import com.reading.common.annotation.Log;
import com.reading.common.core.controller.BaseController;
import com.reading.common.core.domain.AjaxResult;
import com.reading.common.core.page.TableDataInfo;
import com.reading.common.enums.BusinessType;
import com.reading.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论Controller
 * 
 * @author reading
 * @date 2021-03-28
 */
@RestController
@RequestMapping("/app/remark")
public class AppRemarkController extends BaseController
{
    @Autowired
    private IReRemarkService reRemarkService;

    /**
     * 查询评论列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ReRemark reRemark)
    {
        startPage();
        List<ReRemark> list = reRemarkService.selectReRemarkList(reRemark);
        return getDataTable(list);
    }

    /**
     * 导出评论列表
     */
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ReRemark reRemark)
    {
        List<ReRemark> list = reRemarkService.selectReRemarkList(reRemark);
        ExcelUtil<ReRemark> util = new ExcelUtil<ReRemark>(ReRemark.class);
        return util.exportExcel(list, "remark");
    }

    /**
     * 获取评论详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(reRemarkService.selectReRemarkById(id));
    }

    /**
     * 新增评论
     */
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReRemark reRemark)
    {
        return toAjax(reRemarkService.insertReRemark(reRemark));
    }

    /**
     * 修改评论
     */
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReRemark reRemark)
    {
        return toAjax(reRemarkService.updateReRemark(reRemark));
    }

    /**
     * 删除评论
     */
    @Log(title = "评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reRemarkService.deleteReRemarkByIds(ids));
    }
}
