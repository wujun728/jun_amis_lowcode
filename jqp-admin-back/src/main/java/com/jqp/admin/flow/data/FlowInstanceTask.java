package com.jqp.admin.flow.data;

import com.jqp.admin.common.BaseData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

/***
 * @date 2023-05-10 14:46:23
 * @remark 流程实例任务
 */
@Data
@FieldNameConstants
public class FlowInstanceTask extends BaseData {
    //流程实例id
    private Long flowInstanceId;
    //任务名称
    private String taskName;
    //任务id
    private String taskId;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //流转id
    private String edgeId;
    //流转名称
    private String edgeName;
    //候选人列表
    private String candidateUserIds;
    //候选岗位列表
    private String candidatePositionCodes;
    //审核人id
    private Long auditUserId;
    //审核人姓名
    private String auditUserName;
    //单据状态
    private String orderStatus;
    //单据状态名称
    private String orderStatusName;
    //状态字典
    private String orderStatusDic;
    //表名
    private String tableName;
    //关联id
    private Long refId;
    //流程编码
    private String flowCode;
    //流程名称
    private String flowName;
    //流程实例名称
    private String flowInstanceName;
    //审核备注
    private String auditRemark;
    //审核附件
    private String auditFiles;
    //审核图片
    private String auditImgs;
    //企业id
    private Long enterpriseId;
    //查看表单
    private String viewForm;
    //是否通过
    private String pass;
    //创建人id
    private Long createUserId;
    //创建人姓名
    private String createUserName;
}
