package com.ruoyi.web.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 参数配置 信息操作处理
 *
 * @author Lion Li
 */
@Validated
@Api(value = "参数配置控制器", tags = {"参数配置管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {

    private final ISysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @ApiOperation("获取参数配置列表")
    @SaCheckPermission("system:config:list")
    @GetMapping("/list")
    public TableDataInfo<SysConfig> list(SysConfig config, PageQuery pageQuery) {
        return configService.selectPageConfigList(config, pageQuery);
    }

    @ApiOperation("导出参数配置列表")
    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:config:export")
    @PostMapping("/export")
    public void export(SysConfig config, HttpServletResponse response) {
        List<SysConfig> list = configService.selectConfigList(config);
        ExcelUtil.exportExcel(list, "参数数据", SysConfig.class, response);
    }

    /**
     * 根据参数编号获取详细信息
     */
    @ApiOperation("根据参数编号获取详细信息")
    @SaCheckPermission("system:config:query")
    @GetMapping(value = "/{configId}")
    public R<SysConfig> getInfo(@ApiParam("参数ID") @PathVariable Long configId) {
        return R.ok(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @ApiOperation("根据参数键名查询参数值")
    @GetMapping(value = "/configKey/{configKey}")
    public R<Void> getConfigKey(@ApiParam("参数Key") @PathVariable String configKey) {
        return R.ok(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @ApiOperation("新增参数配置")
    @SaCheckPermission("system:config:add")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return R.fail("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @ApiOperation("修改参数配置")
    @SaCheckPermission("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated @RequestBody SysConfig config) {
        if (UserConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return R.fail("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 根据参数键名修改参数配置
     */
    @ApiOperation("根据参数键名修改参数配置")
    @SaCheckPermission("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PutMapping("/updateByKey")
    public R<Void> updateByKey(@RequestBody SysConfig config) {
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @ApiOperation("删除参数配置")
    @SaCheckPermission("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public R<Void> remove(@ApiParam("参数ID串") @PathVariable Long[] configIds) {
        configService.deleteConfigByIds(configIds);
        return R.ok();
    }

    /**
     * 刷新参数缓存
     */
    @ApiOperation("刷新参数缓存")
    @SaCheckPermission("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public R<Void> refreshCache() {
        configService.resetConfigCache();
        return R.ok();
    }
}
