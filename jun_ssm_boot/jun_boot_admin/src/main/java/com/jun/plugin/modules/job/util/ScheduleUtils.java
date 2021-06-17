package com.jun.plugin.modules.job.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.jun.plugin.common.constant.ScheduleConstants;
import com.jun.plugin.common.exception.TaskException;
import com.jun.plugin.common.exception.TaskException.Code;
import com.jun.plugin.modules.job.model.SysJob;

/**
 * 定时任务工具类
 */
public class ScheduleUtils {
	/**
	 * 得到quartz任务类
	 *
	 * @param sysJob
	 *            执行计划
	 * @return 具体执行任务类
	 */
	private static Class<? extends org.quartz.Job> getQuartzJobClass(SysJob job) {
		boolean isConcurrent = "0".equals(job.getConcurrent());
		return isConcurrent ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;
	}

	/**
	 * 构建任务触发对象
	 */
	public static TriggerKey getTriggerKey(Long jobId, String jobGroup) {
		return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
	}

	/**
	 * 构建任务键对象
	 */
	public static JobKey getJobKey(Long jobId, String jobGroup) {
		return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
	}

	/**
	 * 创建定时任务
	 */
	public static void createScheduleJob(Scheduler scheduler, SysJob job) throws SchedulerException, TaskException {
		Class<? extends org.quartz.Job> jobClass = getQuartzJobClass(job);
		// 构建job信息
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(getJobKey(jobId, jobGroup)).build();

		// 表达式调度构建器
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
		cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);

		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobId, jobGroup)).withSchedule(cronScheduleBuilder).build();

		// 放入参数，运行时的方法可以获取
		jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, job);

		// 判断是否存在
		if (scheduler.checkExists(getJobKey(jobId, jobGroup))) {
			// 防止创建时存在数据问题 先移除，然后在执行创建操作
			scheduler.deleteJob(getJobKey(jobId, jobGroup));
		}

		scheduler.scheduleJob(jobDetail, trigger);

		// 暂停任务
		if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())) {
			scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
		}
	}

	/*
	 * 设置定时任务策略
	 */

	/**
	 * 执行错误后执行策略 withMisfireHandlingInstructionDoNothing 不触发立即执行
	 * withMisfireHandlingInstructionIgnoreMisfires 以错过的第一个频率时间立刻开始执行 重做错过的所有频率周期后
	 * withMisfireHandlingInstructionFireAndProceed 以当前时间为触发频率立刻触发一次执行
	 */
	public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob job, CronScheduleBuilder cb) throws TaskException {
		switch (job.getMisfirePolicy()) {
		case ScheduleConstants.MISFIRE_DEFAULT:
			return cb;
		case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
			return cb.withMisfireHandlingInstructionIgnoreMisfires();
		case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
			return cb.withMisfireHandlingInstructionFireAndProceed();
		case ScheduleConstants.MISFIRE_DO_NOTHING:
			return cb.withMisfireHandlingInstructionDoNothing();
		default:
			throw new TaskException("The task misfire policy '" + job.getMisfirePolicy() + "' cannot be used in cron schedule tasks", Code.CONFIG_ERROR);
		}
	}
}