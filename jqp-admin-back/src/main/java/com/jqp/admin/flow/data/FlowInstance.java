package com.jqp.admin.flow.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

/***
 * @date 2023-05-10 14:46:10
 * @remark 流程实例
 */
@Data
@FieldNameConstants
public class FlowInstance extends BaseData {
    //流程部署id
    private Long flowDeployId;
    //流程id
    private Long flowId;
    //关联id
    private Long refId;
    //流程实例名称
    private String name;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //当前任务id
    private String currentTaskId;
    //当前任务名称
    private String currentTaskName;
    //流程编码
    private String flowCode;
    //流程名称
    private String flowName;
    //发起人id
    private Long createUserId;
    //发起人姓名
    private String createUserName;
    //企业id
    private Long enterpriseId;
    //查看表单
    private String viewForm;
}
