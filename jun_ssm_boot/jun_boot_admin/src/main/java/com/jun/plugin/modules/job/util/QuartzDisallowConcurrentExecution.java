package com.jun.plugin.modules.job.util;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

import com.jun.plugin.modules.job.model.SysJob;

/**
 * 定时任务处理（禁止并发执行）
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {

	@Override
	protected void doExecute(JobExecutionContext context, SysJob job) throws Exception {
		JobInvokeUtil.invokeMethod(job);
	}
}
