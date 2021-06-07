package com.zhonghe.active4j.func.timer.service;

import org.springframework.ui.Model;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.func.timer.entity.QuartzJobLogEntity;

/**
 * 
 * @title QuartzJobLogService.java
 * @description 
 * @time  2019年12月10日 上午9:59:44
 * @author guyp
 * @version 1.0
 */
public interface QuartzJobLogService extends IService<QuartzJobLogEntity> {
	
	/**
	 * 
	 * @description
	 *  	删除定时任务日志
	 * @params
	 *      ids 日志ids
	 * @return void
	 * @author guyp
	 * @time 2019年12月12日 上午11:00:53
	 */
	public void delLogs(String ids);
	
	/**
	 * 
	 * @description
	 *  	清空日志表
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月12日 上午11:14:27
	 */
	public void emptyLogs();
	
	/**
	 * 
	 * @description
	 *  	获取日志明细
	 * @params
	 *      id 日志id
	 * @return Model
	 * @author guyp
	 * @time 2019年12月12日 上午11:03:40
	 */
	public Model getLogDetailModel(String id, Model model);
	
}
