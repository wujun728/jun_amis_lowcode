package com.jqp.admin.activity.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;

import java.util.Date;

/***
 * @date 2022-03-04 09:37:57
 * @remark 审核记录
 */
@Data
public class AuditRecord extends BaseData {
    //表名
    private String tableName;
    //关联id
    private Long refId;
    //流程实例id
    private String processInstanceId;
    //流程实例名称
    private String processInstanceName;
    //流程部署id
    private String deploymentId;
    //流程定义id
    private String processDefinitionId;
    //任务id
    private String taskId;
    //任务名称
    private String taskName;
    //流程编号
    private String processKey;
    //审核结果
    private String resultName;
    //图片
    private String imgs;
    //附件
    private String files;
    //审核备注
    private String remark;
    //上一个状态
    private String prevStatus;
    //上一个状态名称
    private String prevStatusName;
    //下一个状态
    private String nextStatus;
    //下一个状态名称
    private String nextStatusName;
    //状态字典
    private String statusDic;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //审核人id
    private Long auditUserId;
    //审核人姓名
    private String auditUserName;
    //企业id
    private Long enterpriseId;
}