package com.jun.plugin.modules.job.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.util.text.Convert;
import com.jun.plugin.modules.job.mapper.SysJobLogMapper;
import com.jun.plugin.modules.job.model.SysJobLog;
import com.jun.plugin.modules.job.service.SysJobLogService;

@Service
public class SysJobLogServiceImpl implements SysJobLogService {

	@Autowired
	private SysJobLogMapper sysJobLogMapper;

	@Override
	public SysJobLog getSysJobLogById(Long jobLogId) {
		return sysJobLogMapper.selectById(jobLogId);
	}

	@Override
	public void addJobLog(SysJobLog jobLog) {
		jobLog.setJobLogId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
		jobLog.setCreateTime(new Date());
		sysJobLogMapper.insert(jobLog);
	}

	@Override
	public List<SysJobLog> listSysLogJobs(SysJobLog sysJobLog) {
		QueryWrapper<SysJobLog> queryWrapper = new QueryWrapper<SysJobLog>();
		if (StringUtils.isNotEmpty(sysJobLog.getJobName())) {
			queryWrapper.like("job_name", sysJobLog.getJobName());
		}
		if (StringUtils.isNotEmpty(sysJobLog.getStatus())) {
			queryWrapper.eq("status", sysJobLog.getStatus());
		}
		queryWrapper.orderByDesc("create_time");
		return sysJobLogMapper.selectList(queryWrapper);
	}

	@Override
	public void deleteJobLogByIds(String jobLogIdStr) {
		Long[] jobLogIds = Convert.toLongArray(",", jobLogIdStr);
		QueryWrapper<SysJobLog> queryWrapper = new QueryWrapper<SysJobLog>();
		queryWrapper.in("job_log_id", Arrays.asList(jobLogIds));
		sysJobLogMapper.delete(queryWrapper);
	}

}
