package com.jun.plugin.modules.job.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.util.text.Convert;
import com.jun.plugin.modules.job.mapper.SysJobLogMapper;
import com.jun.plugin.modules.job.model.SysJobLog;
import com.jun.plugin.modules.job.service.SysJobLogService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysJobLogServiceImpl implements SysJobLogService {

	@Autowired
	private SysJobLogMapper sysJobLogMapper;

	@Override
	public SysJobLog getSysJobLogById(Long jobLogId) {
		return sysJobLogMapper.selectByPrimaryKey(jobLogId);
	}

	@Override
	public void addJobLog(SysJobLog jobLog) {
		jobLog.setJobLogId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
		jobLog.setCreateTime(new Date());
		sysJobLogMapper.insert(jobLog);
	}

	@Override
	public List<SysJobLog> listSysLogJobs(SysJobLog sysJobLog) {
		Example example = new Example(SysJobLog.class);
		Example.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(sysJobLog.getJobName())) {
			criteria.andLike("jobName", "%" + sysJobLog.getJobName() + "%");
		}
		if (StringUtils.isNotEmpty(sysJobLog.getStatus())) {
			criteria.andEqualTo("status", "%" + sysJobLog.getStatus());
		}
		example.orderBy("createTime").desc();
		return sysJobLogMapper.selectByExample(example);
	}

	@Override
	public void deleteJobLogByIds(String jobLogIdStr) {
		Long[] jobLogIds = Convert.toLongArray(",", jobLogIdStr);
		Example example = new Example(SysJobLog.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("jobLogId", Arrays.asList(jobLogIds));
		sysJobLogMapper.deleteByExample(example);
	}

}
