package com.jun.biz.manager.dto.permission;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created on 2020/10/15 16:46
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class CreatePermissionDTO {
    /**
     * 父级权限id       db_column: pid
     */
    private Long pid;

    /**
     * 名称       db_column: title
     */
    @NotBlank
    private String title;

    /**
     * 权限值       db_column: code
     */
    @NotBlank
    private String code;

    /**
     * 图标       db_column: icon
     */

    private String icon;

    /**
     * 权限类型：1->菜单；2->按钮（接口绑定权限）       db_column: type
     */
    @NotNull
    private Integer type;

    /**
     * 前端资源路径       db_column: href
     */
    private String href;


    /**
     * 排序       db_column: weight
     */
    @NotNull
    private Double weight;
}
