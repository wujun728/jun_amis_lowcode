package com.jqp.admin.flow.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/***
 * @date 2023-05-10 11:43:06
 * @remark 流程部署
 */
@Data
@FieldNameConstants
public class FlowDeploy extends BaseData {
    //编号
    private String code;
    //名称
    private String name;
    //当前版本
    private Integer version;
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
    //流程id
    private Long flowId;
    //前置接口
    private String beforeApi;
    //后置接口
    private String afterApi;
    //查看表单
    private String viewForm;
}
