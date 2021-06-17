package com.jun.plugin.modules.job.service;

import java.util.List;

import com.jun.plugin.modules.job.model.SysJobLog;

public interface SysJobLogService {

	void addJobLog(SysJobLog jobLog);

	SysJobLog getSysJobLogById(Long jobLogId);
	
	List<SysJobLog> listSysLogJobs(SysJobLog sysJobLog);

	void deleteJobLogByIds(String jobLogIdStr);


}
