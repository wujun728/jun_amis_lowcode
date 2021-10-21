package com.jun.biz.manager.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jun.biz.common.annotation.PerCode;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.manager.dto.permission.CreatePermissionDTO;
import com.jun.biz.manager.dto.permission.ModifyPermissionDTO;
import com.jun.biz.manager.service.PermissionService;
import com.jun.biz.manager.vo.admin.PermissionVO;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * Created on 2020/10/15 12:51
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */
@Validated
@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PerCode("permission:mgt")
    @GetMapping("list")
    public ResultVO<List<PermissionVO>> list() {
        return permissionService.list();
    }

    @GetMapping("tree")
    public ResultVO<List<PermissionVO>> tree() {
        return permissionService.permissionTree();
    }

    @PerCode("permission:add")
    @PostMapping("create")
    public ResultVO<Boolean> create(@Validated @RequestBody CreatePermissionDTO dto) {
        return permissionService.create(dto);
    }

    @PerCode("permission:update")
    @PostMapping("modify")
    public ResultVO<Boolean> modify(@Validated @RequestBody ModifyPermissionDTO dto) {
        return permissionService.modify(dto);
    }

    @PerCode("permission:del")
    @DeleteMapping("delete")
    public ResultVO<Boolean> delete(@RequestBody @Validated @NotNull @Size(min = 1) Set<Long> id) {
        return permissionService.delete(id);
    }
}
