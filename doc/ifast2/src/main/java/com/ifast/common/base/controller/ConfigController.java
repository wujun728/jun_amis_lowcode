package com.ifast.common.base.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.AdminBaseController;
import com.ifast.common.base.domain.ConfigDO;
import com.ifast.common.base.service.ConfigService;
import com.ifast.common.utils.Result;

/**
 * 
 * 
 * @author Aron
 * @email izenglong@163.com
 * @date 2018-04-06 01:05:22
 */

@Controller
@RequestMapping("/common/config")
public class ConfigController extends AdminBaseController {
    @Autowired
    private ConfigService configService;
    
    @GetMapping()
    @RequiresPermissions("common:config:config")
    String Config() {
        return "common/config/config";
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("common:config:config")
    public Result<Page<ConfigDO>> list(ConfigDO configDTO) {
        Page<ConfigDO> page = (Page<ConfigDO>) configService.page(getPage(ConfigDO.class), configService.convertToEntityWrapper("k", configDTO.getK()));
        
        return Result.ok(page);
    }
    
    @GetMapping("/add")
    @RequiresPermissions("common:config:add")
    String add() {
        return "common/config/add";
    }
    
    @GetMapping("/edit/{id}")
    @RequiresPermissions("common:config:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        ConfigDO config = configService.getById(id);
        model.addAttribute("config", config);
        return "common/config/edit";
    }

    /**
     * 保存
     */
    @Log("添加系统配置")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:config:add")
    public Result<String> save(ConfigDO config) {
        if (configService.save(config)) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 修改
     */
    @Log("更新系统配置")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("common:config:edit")
    public Result<String> update(ConfigDO config) {
        configService.updateById(config);
        return Result.ok();
    }

    /**
     * 删除
     */
    @Log("删除系统配置")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("common:config:remove")
    public Result<String> remove(Long id) {
        configService.removeById(id);
        return Result.ok();
    }

    /**
     * 删除
     */
    @Log("批量删除系统配置")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:config:batchRemove")
    public Result<String> remove(@RequestParam("ids[]") Long[] ids) {
        configService.removeByIds( Arrays.asList(ids));
        return Result.ok();
    }

}
