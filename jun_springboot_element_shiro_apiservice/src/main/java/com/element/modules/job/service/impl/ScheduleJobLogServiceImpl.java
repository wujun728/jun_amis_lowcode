package com.element.modules.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.element.common.utils.PageUtils;
import com.element.common.utils.Query;
import com.element.modules.job.mapper.ScheduleJobLogMapper;
import com.element.modules.job.entity.ScheduleJobLogEntity;
import com.element.modules.job.service.ScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements ScheduleJobLogService {

    @Override
    public PageUtils listJobLogByPage(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");
        IPage<ScheduleJobLogEntity> page = this.page(
                new Query<ScheduleJobLogEntity>().getPage(params),
                new QueryWrapper<ScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId), "job_id", jobId)
        );
        return new PageUtils(page);
    }

}
