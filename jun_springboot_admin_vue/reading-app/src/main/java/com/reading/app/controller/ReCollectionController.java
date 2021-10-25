package com.reading.app.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reading.common.annotation.Log;
import com.reading.common.core.controller.BaseController;
import com.reading.common.core.domain.AjaxResult;
import com.reading.common.enums.BusinessType;
import com.reading.app.domain.ReCollection;
import com.reading.app.service.IReCollectionService;
import com.reading.common.utils.poi.ExcelUtil;
import com.reading.common.core.page.TableDataInfo;

/**
 * 收藏列Controller
 * 
 * @author reading
 * @date 2021-03-29
 */
@RestController
@RequestMapping("/re/collect")
public class ReCollectionController extends BaseController
{
    @Autowired
    private IReCollectionService reCollectionService;

    /**
     * 查询收藏列列表
     */
    @PreAuthorize("@ss.hasPermi('re:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(ReCollection reCollection)
    {
        startPage();
        List<ReCollection> list = reCollectionService.selectReCollectionList(reCollection);
        return getDataTable(list);
    }

    /**
     * 导出收藏列列表
     */
    @PreAuthorize("@ss.hasPermi('re:collect:export')")
    @Log(title = "收藏列", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ReCollection reCollection)
    {
        List<ReCollection> list = reCollectionService.selectReCollectionList(reCollection);
        ExcelUtil<ReCollection> util = new ExcelUtil<ReCollection>(ReCollection.class);
        return util.exportExcel(list, "collect");
    }

    /**
     * 获取收藏列详细信息
     */
    @PreAuthorize("@ss.hasPermi('re:collect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(reCollectionService.selectReCollectionById(id));
    }

    /**
     * 新增收藏列
     */
    @PreAuthorize("@ss.hasPermi('re:collect:add')")
    @Log(title = "收藏列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReCollection reCollection)
    {
        return toAjax(reCollectionService.insertReCollection(reCollection));
    }

    /**
     * 修改收藏列
     */
    @PreAuthorize("@ss.hasPermi('re:collect:edit')")
    @Log(title = "收藏列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReCollection reCollection)
    {
        return toAjax(reCollectionService.updateReCollection(reCollection));
    }

    /**
     * 删除收藏列
     */
    @PreAuthorize("@ss.hasPermi('re:collect:remove')")
    @Log(title = "收藏列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reCollectionService.deleteReCollectionByIds(ids));
    }
}
