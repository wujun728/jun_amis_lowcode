package com.htmall.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htmall.entity.SysLog;
import com.htmall.mapper.SysLogMapper;
import com.htmall.service.ISysLogService;

/**
 *
 * SysLog 表数据服务层接口实现类
 *
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

	public static final Logger logger = Logger.getLogger(SysLogServiceImpl.class);

	@Override
	public void insertLog(String title, String uname, String url, String parms) {
		SysLog sysLog = new SysLog();
		sysLog.setCreateTime(new Date());
		sysLog.setTitle(title);
		sysLog.setUserName(uname);
		sysLog.setUrl(url);
		sysLog.setParams(parms);
		this.save(sysLog);
		logger.debug("记录日志:" + sysLog.toString());
	}

}