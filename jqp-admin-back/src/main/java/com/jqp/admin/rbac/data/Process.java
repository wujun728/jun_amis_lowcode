package com.jqp.admin.rbac.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-02-22 09:46:43
 * @remark 流程
 */
@Data
public class Process extends BaseData {
    //流程编号
    private String code;
    //流程名称
    private String name;
    //主表
    private String mainTable;
    //审核记录表
    private String recordTable;
    //状态字段
    private String statusField;
    //记录主表id字段
    private String recordMainIdField;
    //备注
    private String remark;
}