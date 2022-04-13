package com.element.modules.sys.controller;

import com.element.common.annotation.SysLog;
import com.element.common.utils.Constant;
import com.element.common.utils.PageUtils;
import com.element.common.utils.ResultVo;
import com.element.common.validator.Assert;
import com.element.common.validator.ValidatorUtils;
import com.element.common.validator.group.AddGroup;
import com.element.common.validator.group.UpdateGroup;
import com.element.modules.sys.entity.SysUserEntity;
import com.element.modules.sys.form.PasswordForm;
import com.element.modules.sys.service.SysUserRoleService;
import com.element.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;


    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public ResultVo list(@RequestParam Map<String, Object> params) {
        // 只有超级管理员，才能查看所有管理员列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }
        PageUtils page = sysUserService.listUserByPage(params);
        return ResultVo.ok().put("page", page);
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public ResultVo info(@PathVariable("userId") Long userId) {
        // 获取角色信息
        SysUserEntity user = sysUserService.getById(userId);
        // 获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.listRoleIdByUserId(userId);
        user.setRoleIdList(roleIdList);
        return ResultVo.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public ResultVo save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        user.setCreateUserId(getUserId());
        sysUserService.saveUser(user);
        return ResultVo.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public ResultVo update(@RequestBody SysUserEntity sysUser) {
        ValidatorUtils.validateEntity(sysUser, UpdateGroup.class);
        sysUser.setCreateUserId(getUserId());
        sysUserService.update(sysUser);
        return ResultVo.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public ResultVo delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return ResultVo.error("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, getUserId())) {
            return ResultVo.error("当前用户不能删除");
        }
        sysUserService.deleteBatch(userIds);
        return ResultVo.ok();
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    public ResultVo password(@RequestBody PasswordForm form) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");
        // sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
        // 更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return ResultVo.error("原密码不正确");
        }
        return ResultVo.ok();
    }

}
