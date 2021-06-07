package com.zhonghe.active4j.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.system.dao.SysLogDao;
import com.zhonghe.active4j.system.entity.SysLogEntity;
import com.zhonghe.active4j.system.service.SysLogService;

/**
 * 日志管理service类
 * @author 38943
 *
 */
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

	@Autowired
	private SysLogService sysLogService;
	
	public void addLog(SysLogEntity log) {
		
		//基础变量赋值
		log.setCreateName("system");
		
		//有些方法参数特别长，特殊处理一下
		if(StringUtils.length(log.getParams()) > 500) {
			log.setParams(StringUtils.substring(log.getParams(), 0, 500) + "...");
		}
		
		sysLogService.save(log);
	}
	
}
