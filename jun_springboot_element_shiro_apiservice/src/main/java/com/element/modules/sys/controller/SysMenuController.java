package com.element.modules.sys.controller;

import com.element.common.annotation.SysLog;
import com.element.common.exception.CustomException;
import com.element.common.utils.Constant;
import com.element.common.utils.ResultVo;
import com.element.modules.sys.entity.SysMenuEntity;
import com.element.modules.sys.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public ResultVo list() {
        List<SysMenuEntity> menuList = sysMenuService.list();
        HashMap<Long, SysMenuEntity> menuMap = new HashMap<>();
        for (SysMenuEntity sysMenu : menuList) {
            menuMap.put(sysMenu.getMenuId(), sysMenu);
        }
        for (SysMenuEntity sysMenu : menuList) {
            SysMenuEntity parent = menuMap.get(sysMenu.getParentId());
            if (Objects.nonNull(parent)) {
                sysMenu.setParentName(parent.getName());
            }
        }
        return ResultVo.ok().put("menuList", menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public ResultVo select() {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.listMenuNotButton();
        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);
        return ResultVo.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public ResultVo info(@PathVariable("menuId") Long menuId) {
        SysMenuEntity menu = sysMenuService.getById(menuId);
        return ResultVo.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @SysLog("保存菜单")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public ResultVo save(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);
        sysMenuService.save(menu);
        return ResultVo.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public ResultVo update(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);
        sysMenuService.updateById(menu);
        return ResultVo.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除菜单")
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public ResultVo delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 26) {
            return ResultVo.error("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysMenuEntity> menuList = sysMenuService.listMenuByParentId(menuId);
        if (menuList.size() > 0) {
            return ResultVo.error("请先删除子菜单或按钮");
        }
        sysMenuService.delete(menuId);
        return ResultVo.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new CustomException("菜单名称不能为空");
        }
        if (menu.getParentId() == null) {
            throw new CustomException("上级菜单不能为空");
        }
        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getComponent())) {
                throw new CustomException("组件路径不能为空");
            }
        }
        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }
        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new CustomException("上级菜单只能为目录类型");
            }
            return;
        }
        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new CustomException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
