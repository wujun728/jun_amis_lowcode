package com.jqp.admin.rbac.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-02-23 15:23:26
 * @remark menu_url
 */
@Data
public class MenuUrl extends BaseData {
    //菜单id
    private Long menuId;
    //url
    private String url;
    //名称
    private String name;
}