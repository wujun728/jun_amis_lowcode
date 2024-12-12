package com.jqp.admin.activity.data;

import lombok.Data;

/***
 * 新建工作流用的参数
 */
@Data
public class ModelData {
    private String key;
    private String name;
    private String description;
    private int revision = 1;
    private String id;
    //初始化sql
    private String initSql;
    //任务执行前sql
    private String beforeApi;
    //任务执行后sql
    private String afterApi;
    //主表
    private String tableName;
    //状态字段
    private String statusField;
    //状态字典
    private String statusDic;
    //查看表单
    private String viewForm;
}
