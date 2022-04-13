package com.element.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 */
@TableName("sys_role")
@ApiModel(value = "SysRoleEntity对象", description = "角色")
public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建者ID")
    @TableField("create_user_id")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @TableField(exist = false)
    private List<Long> menuIdList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

}
