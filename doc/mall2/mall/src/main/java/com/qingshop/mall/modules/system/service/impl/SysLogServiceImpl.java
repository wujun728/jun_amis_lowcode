package com.qingshop.mall.modules.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.common.utils.idwork.DistributedIdWorker;
import com.qingshop.mall.modules.system.entity.SysLog;
import com.qingshop.mall.modules.system.mapper.SysLogMapper;
import com.qingshop.mall.modules.system.service.ISysLogService;

/**
 * SysLog 表数据服务层接口实现类
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

	@Override
	public void insertLog(String title, String uname, String url, String parms) {
		SysLog sysLog = new SysLog();
		sysLog.setLogId(DistributedIdWorker.nextId());
		sysLog.setTitle(title);
		sysLog.setUserName(uname);
		sysLog.setUrl(url);
		sysLog.setParams(parms);
		save(sysLog);
	}

	@Override
	public void deleteAll() {
		remove(null);
	}

}