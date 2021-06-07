package com.zhonghe.active4j.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.system.entity.SysLogEntity;

public interface SysLogService extends IService<SysLogEntity> {
	
	public void addLog(SysLogEntity log);
	
}
