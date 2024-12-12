package com.jqp.admin.flow.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/***
 * @date 2023-05-08 17:39:04
 * @remark 流程
 */
@Data
@FieldNameConstants
public class Flow extends BaseData {
    //编号
    private String code;
    //名称
    private String name;
    //最新版本
    private Integer lastVersion;
    //流程数据
    private String graphData;
    //表名称
    private String tableName;
    //状态字段
    private String statusField;
    //状态字典
    private String statusDic;
    //扩展配置
    private String extraJson;
    //前置接口
    private String beforeApi;
    //后置接口
    private String afterApi;
    //查看表单
    private String viewForm;
}
