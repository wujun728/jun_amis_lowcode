package com.jun.biz.manager.vo.admin;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2020/10/14 18:29
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */

@Data
public class PermissionVO implements Comparable<PermissionVO> {


    private Long id;

    /**
     * 父级权限id       db_column: pid
     */

    private Long pid;

    /**
     * 名称       db_column: title
     */

    private String title;

    /**
     * 权限值       db_column: code
     */

    private String code;

    /**
     * 图标       db_column: icon
     */

    private String icon;

    /**
     * 权限类型：1->菜单；2->按钮（接口绑定权限）       db_column: type
     */

    private Integer type;

    /**
     * 前端资源路径       db_column: href
     */

    private String href;

    /**
     * 启用状态；0->禁用；1->启用       db_column: status
     */

    private Integer status;

    /**
     * 创建时间       db_column: create_time
     */

    private Date createTime;

    /**
     * 排序       db_column: weight
     */

    private Double weight;


    private List<PermissionVO> children = new ArrayList<>();

    @Override
    public int compareTo(PermissionVO o) {
        return o.getWeight().compareTo(this.getWeight());

    }
}
