package com.element.modules.sys.controller;

import com.element.common.annotation.SysLog;
import com.element.common.utils.Constant;
import com.element.common.utils.PageUtils;
import com.element.common.utils.ResultVo;
import com.element.common.validator.ValidatorUtils;
import com.element.modules.sys.entity.SysRoleEntity;
import com.element.modules.sys.service.SysRoleMenuService;
import com.element.modules.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 角色列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public ResultVo list(@RequestParam Map<String, Object> params) {
        // 如果不是超级管理员，则只查询自己创建的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }
        PageUtils page = sysRoleService.listRoleByPage(params);
        return ResultVo.ok().put("page", page);
    }

    /**
     * 角色列表
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:role:select")
    public ResultVo select() {
        Map<String, Object> map = new HashMap<>();
        // 如果不是超级管理员，则只查询自己所拥有的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            map.put("create_user_id", getUserId());
        }
        List<SysRoleEntity> list = sysRoleService.listByMap(map);
        return ResultVo.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public ResultVo info(@PathVariable("roleId") Long roleId) {
        SysRoleEntity role = sysRoleService.getById(roleId);
        // 查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.listMenuIdByRoleId(roleId);
        role.setMenuIdList(menuIdList);
        return ResultVo.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public ResultVo save(@RequestBody SysRoleEntity sysRole) {
        ValidatorUtils.validateEntity(sysRole);
        sysRole.setCreateUserId(getUserId());
        sysRoleService.saveRole(sysRole);
        return ResultVo.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public ResultVo update(@RequestBody SysRoleEntity sysRole) {
        ValidatorUtils.validateEntity(sysRole);
        sysRole.setCreateUserId(getUserId());
        sysRoleService.update(sysRole);
        return ResultVo.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public ResultVo delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return ResultVo.ok();
    }

}
