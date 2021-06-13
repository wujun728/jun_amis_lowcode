package com.qingshop.mall.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingshop.mall.modules.job.entity.SysJobLog;

/**
 * 定时任务调度日志表 服务类
 */
public interface ISysJobLogService extends IService<SysJobLog> {

	void addJobLog(SysJobLog jobLog);

	void deleteJobByIds(String ids);

	void cleanJobLog();

}
