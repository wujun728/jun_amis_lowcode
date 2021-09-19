package com.jun.plugin.modules.job.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.plugin.common.constant.ScheduleConstants;
import com.jun.plugin.common.exception.TaskException;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.common.util.text.Convert;
import com.jun.plugin.modules.job.mapper.SysJobMapper;
import com.jun.plugin.modules.job.model.SysJob;
import com.jun.plugin.modules.job.service.SysJobService;
import com.jun.plugin.modules.job.util.CronUtils;
import com.jun.plugin.modules.job.util.ScheduleUtils;

@Service
public class SysJobServiceImpl implements SysJobService {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private SysJobMapper sysJobMapper;

	/**
	 * 项目启动时，初始化定时器 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
	 */
	@PostConstruct
	public void init() throws SchedulerException, TaskException {
		List<SysJob> jobList = sysJobMapper.selectList(null);
		for (SysJob sysJob : jobList) {
			updateSchedulerJob(sysJob, sysJob.getJobGroup());
		}
	}

	/**
	 * 新增任务
	 * 
	 * @param job
	 *            调度信息 调度信息
	 */
	@Override
	@Transactional
	public int insertJob(SysJob job) throws SchedulerException, TaskException {
		job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		int rows = sysJobMapper.insert(job);
		if (rows > 0) {
			ScheduleUtils.createScheduleJob(scheduler, job);
		}
		return rows;
	}

	/**
	 * 更新任务的时间表达式
	 * 
	 * @param job
	 *            调度信息
	 */
	@Override
	@Transactional
	public int updateJob(SysJob job) throws SchedulerException, TaskException {
		SysJob properties = sysJobMapper.selectById(job);
		int rows = sysJobMapper.updateById(job);
		if (rows > 0) {
			updateSchedulerJob(job, properties.getJobGroup());
		}
		return rows;
	}

	@Override
	@Transactional
	public void deleteJobByIds(String ids) throws SchedulerException {
		Long[] jobIds = Convert.toLongArray(ids);
		for (Long jobId : jobIds) {
			SysJob sysjob = new SysJob();
			sysjob.setJobId(jobId);
			SysJob job = sysJobMapper.selectById(sysjob);
			deleteJob(job);
		}
	}

	/**
	 * 删除任务后，所对应的trigger也将被删除
	 * 
	 * @param job
	 *            调度信息
	 */
	@Override
	@Transactional
	public int deleteJob(SysJob job) throws SchedulerException {
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		int rows = sysJobMapper.deleteById(job);
		if (rows > 0) {
			scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
		}
		return rows;
	}

	/**
	 * 任务调度状态修改
	 * 
	 * @param job
	 *            调度信息
	 */
	@Override
	@Transactional
	public int changeStatus(SysJob job) throws SchedulerException {
		int rows = 0;
		String status = job.getStatus();
		if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
			rows = resumeJob(job);
		} else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
			rows = pauseJob(job);
		}
		return rows;
	}

	/**
	 * 暂停任务
	 * 
	 * @param job
	 *            调度信息
	 */
	@Override
	@Transactional
	public int pauseJob(SysJob job) throws SchedulerException {
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		int rows = sysJobMapper.updateById(job);
		if (rows > 0) {
			scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
		}
		return rows;
	}

	/**
	 * 恢复任务
	 * 
	 * @param job
	 *            调度信息
	 */
	@Override
	@Transactional
	public int resumeJob(SysJob job) throws SchedulerException {
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
		int rows = sysJobMapper.updateById(job);
		if (rows > 0) {
			scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
		}
		return rows;
	}

	/**
	 * 立即运行任务
	 * 
	 * @param job
	 *            调度信息
	 */
	@Override
	@Transactional
	public void run(SysJob job) throws SchedulerException {
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		SysJob properties = sysJobMapper.selectById(job);
		// 参数
		JobDataMap dataMap = new JobDataMap();
		dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
		scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
	}

	/**
	 * 更新任务
	 * 
	 * @param job
	 *            调度信息
	 * @param jobGroup
	 *            任务组名
	 */
	public void updateSchedulerJob(SysJob job, String jobGroup) throws SchedulerException, TaskException {
		Long jobId = job.getJobId();
		// 判断是否存在
		JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
		if (scheduler.checkExists(jobKey)) {
			// 防止创建时存在数据问题 先移除，然后在执行创建操作
			scheduler.deleteJob(jobKey);
		}
		ScheduleUtils.createScheduleJob(scheduler, job);
	}

	/**
	 * 校验cron表达式是否有效
	 * 
	 * @param cronExpression
	 *            表达式
	 * @return 结果
	 */
	@Override
	public boolean checkCronExpressionIsValid(String cronExpression) {
		return CronUtils.isValid(cronExpression);
	}

	@Override
	public List<SysJob> listSysJobs(SysJob sysJob) {
		QueryWrapper<SysJob> queryWrapper = new QueryWrapper<SysJob>();
		if (StringUtils.isNotEmpty(sysJob.getJobName())) {
			queryWrapper.like("job_name", sysJob.getJobName());
		}
		return sysJobMapper.selectList(queryWrapper);
	}

	@Override
	public SysJob getSysJobById(Long jobId) {
		SysJob sysJob = new SysJob();
		sysJob.setJobId(jobId);
		return sysJobMapper.selectById(sysJob);
	}

}
