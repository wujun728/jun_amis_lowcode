package com.jun.plugin.modules.job.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.jun.plugin.common.exception.TaskException;
import com.jun.plugin.modules.job.model.SysJob;

public interface SysJobService {
	
	void deleteJobByIds(String ids) throws SchedulerException;

	boolean checkCronExpressionIsValid(String cronExpression);

	int deleteJob(SysJob job) throws SchedulerException;

	int insertJob(SysJob job) throws SchedulerException, TaskException;

	int updateJob(SysJob sysJob) throws SchedulerException, TaskException;

	void run(SysJob job) throws SchedulerException;

	int changeStatus(SysJob job) throws SchedulerException;

	int pauseJob(SysJob job) throws SchedulerException;

	int resumeJob(SysJob job) throws SchedulerException;
	
	List<SysJob> listSysJobs(SysJob sysJob);

	SysJob getSysJobById(Long jobId);

}
