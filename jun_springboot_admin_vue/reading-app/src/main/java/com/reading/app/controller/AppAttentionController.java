package com.reading.app.controller;

import com.reading.app.domain.ReAttention;
import com.reading.app.service.IReAttentionService;
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
 * 关注Controller
 * 
 * @author ruoyi
 * @date 2021-03-29
 */
@RestController
@RequestMapping("/app/attention")
public class AppAttentionController extends BaseController
{
    @Autowired
    private IReAttentionService reAttentionService;

    /**
     * 查询关注列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ReAttention reAttention)
    {
        startPage();
        List<ReAttention> list = reAttentionService.selectReAttentionList(reAttention);
        return getDataTable(list);
    }

    /**
     * 导出关注列表
     */
    @Log(title = "关注", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ReAttention reAttention)
    {
        List<ReAttention> list = reAttentionService.selectReAttentionList(reAttention);
        ExcelUtil<ReAttention> util = new ExcelUtil<ReAttention>(ReAttention.class);
        return util.exportExcel(list, "attention");
    }

    /**
     * 获取关注详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(reAttentionService.selectReAttentionById(id));
    }

    /**
     * 新增关注
     */
    @Log(title = "关注", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReAttention reAttention)
    {
        return toAjax(reAttentionService.insertReAttention(reAttention));
    }

    /**
     * 修改关注
     */
    @Log(title = "关注", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReAttention reAttention)
    {
        return toAjax(reAttentionService.updateReAttention(reAttention));
    }

    /**
     * 删除关注
     */
    @Log(title = "关注", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reAttentionService.deleteReAttentionByIds(ids));
    }
}
