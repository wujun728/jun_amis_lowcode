package com.qingshop.mall.modules.job.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.common.utils.text.Convert;
import com.qingshop.mall.modules.job.entity.SysJobLog;
import com.qingshop.mall.modules.job.mapper.SysJobLogMapper;
import com.qingshop.mall.modules.job.service.ISysJobLogService;

/**
 * 定时任务调度日志表 服务实现类
 */
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements ISysJobLogService {

	@Override
	public void addJobLog(SysJobLog jobLog) {
		jobLog.setJobLogId(DistributedIdWorker.nextId());
		jobLog.setCreateTime(new Date());
		save(jobLog);
	}

	@Override
	public void deleteJobByIds(String ids) {
		String[] idArray = Convert.toStrArray(ids);
		List<String> idList = Stream.of(idArray).collect(Collectors.toList());
		removeByIds(idList);
	}

	@Override
	public void cleanJobLog() {
		remove(null);
	}

}
