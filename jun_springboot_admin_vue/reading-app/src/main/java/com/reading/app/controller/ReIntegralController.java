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
import com.reading.app.domain.ReIntegral;
import com.reading.app.service.IReIntegralService;
import com.reading.common.utils.poi.ExcelUtil;
import com.reading.common.core.page.TableDataInfo;

/**
 * 积分Controller
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@RestController
@RequestMapping("/re/integral")
public class ReIntegralController extends BaseController
{
    @Autowired
    private IReIntegralService reIntegralService;

    /**
     * 查询积分列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ReIntegral reIntegral)
    {
        startPage();
        List<ReIntegral> list = reIntegralService.selectReIntegralList(reIntegral);
        return getDataTable(list);
    }

    /**
     * 导出积分列表
     */
    @Log(title = "积分", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ReIntegral reIntegral)
    {
        List<ReIntegral> list = reIntegralService.selectReIntegralList(reIntegral);
        ExcelUtil<ReIntegral> util = new ExcelUtil<ReIntegral>(ReIntegral.class);
        return util.exportExcel(list, "integral");
    }

    /**
     * 获取积分详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(reIntegralService.selectReIntegralById(id));
    }

    /**
     * 新增积分
     */
    @Log(title = "积分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReIntegral reIntegral)
    {
        return toAjax(reIntegralService.insertReIntegral(reIntegral));
    }

    /**
     * 修改积分
     */
    @Log(title = "积分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReIntegral reIntegral)
    {
        return toAjax(reIntegralService.updateReIntegral(reIntegral));
    }

    /**
     * 删除积分
     */
    @Log(title = "积分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(reIntegralService.deleteReIntegralByIds(ids));
    }
}
