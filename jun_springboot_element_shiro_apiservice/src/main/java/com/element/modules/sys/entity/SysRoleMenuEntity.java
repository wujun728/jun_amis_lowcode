package com.element.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 */
@TableName("sys_role_menu")
@ApiModel(value = "SysRoleMenuEntity对象", description = "角色与菜单对应关系")
public class SysRoleMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("menu_id")
    private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}
