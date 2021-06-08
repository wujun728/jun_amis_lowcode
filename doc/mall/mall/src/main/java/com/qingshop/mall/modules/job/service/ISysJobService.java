package com.qingshop.mall.modules.job.service;

import org.quartz.SchedulerException;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.common.exception.TaskException;
import com.qingshop.mall.modules.job.entity.SysJob;

/**
 * <p>
 * 定时任务调度表 服务类
 * </p>
 * @since 2019-10-16
 */
public interface ISysJobService extends IService<SysJob> {

	void deleteJobByIds(String ids) throws SchedulerException;

	boolean checkCronExpressionIsValid(String cronExpression);

	int deleteJob(SysJob job) throws SchedulerException;

	int insertJob(SysJob job) throws SchedulerException, TaskException;

	int updateJob(SysJob sysJob) throws SchedulerException, TaskException;

	void run(SysJob job) throws SchedulerException;

	int changeStatus(SysJob job) throws SchedulerException;

	int pauseJob(SysJob job) throws SchedulerException;

	int resumeJob(SysJob job) throws SchedulerException;

}
