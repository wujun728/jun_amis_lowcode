package cn.iocoder.yudao.module.bpm.framework.activiti.core.behavior.script.impl;

import cn.iocoder.yudao.framework.datapermission.core.annotation.DataPermission;
import cn.iocoder.yudao.module.bpm.enums.definition.BpmTaskRuleScriptEnum;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 分配给发起人的一级 Leader 审批的 Script 实现类
 *
 * @author 芋道源码
 */
@Component
public class BpmTaskAssignLeaderX1Script extends BpmTaskAssignLeaderAbstractScript {

    @Override
    @DataPermission(enable = false) // 不需要处理数据权限， 不然会有问题，查询不到数据
    public Set<Long> calculateTaskCandidateUsers(TaskEntity task) {
        return calculateTaskCandidateUsers(task, 1);
    }

    @Override
    public BpmTaskRuleScriptEnum getEnum() {
        return BpmTaskRuleScriptEnum.LEADER_X1;
    }

}
