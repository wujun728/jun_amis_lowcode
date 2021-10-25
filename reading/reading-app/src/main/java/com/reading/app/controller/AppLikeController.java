package com.reading.app.controller;

import com.reading.app.domain.ReLike;
import com.reading.app.service.IReLikeService;
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
 * 点赞Controller
 * 
 * @author reading
 * @date 2021-03-29
 */
@RestController
@RequestMapping("/app/like")
public class AppLikeController extends BaseController
{
    @Autowired
    private IReLikeService reLikeService;

    /**
     * 查询点赞列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ReLike reLike)
    {
        startPage();
        List<ReLike> list = reLikeService.selectReLikeList(reLike);
        return getDataTable(list);
    }

    /**
     * 导出点赞列表
     */
    @Log(title = "点赞", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ReLike reLike)
    {
        List<ReLike> list = reLikeService.selectReLikeList(reLike);
        ExcelUtil<ReLike> util = new ExcelUtil<ReLike>(ReLike.class);
        return util.exportExcel(list, "like");
    }

    /**
     * 获取点赞详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(reLikeService.selectReLikeById(id));
    }

    /**
     * 新增点赞
     */
    @Log(title = "点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReLike reLike)
    {
        int ret = reLikeService.insertReLike(reLike);
        if(ret>0){
            return AjaxResult.success("点赞成功");
        }
        return AjaxResult.success("已经点赞了");
    }

    /**
     * 修改点赞
     */
    @Log(title = "点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReLike reLike)
    {
        return toAjax(reLikeService.updateReLike(reLike));
    }

    /**
     * 删除点赞
     */
    @Log(title = "点赞", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reLikeService.deleteReLikeByIds(ids));
    }
}
