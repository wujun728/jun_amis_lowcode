package cn.iocoder.yudao.module.bpm.controller.admin.task.vo.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@ApiModel("管理后台 - 通过流程任务的 Request VO")
@Data
public class BpmTaskApproveReqVO {

    @ApiModelProperty(value = "任务编号", required = true, example = "1024")
    @NotEmpty(message = "任务编号不能为空")
    private String id;

    @ApiModelProperty(value = "审批意见", required = true, example = "不错不错！")
    @NotEmpty(message = "审批意见不能为空")
    private String comment;

}
