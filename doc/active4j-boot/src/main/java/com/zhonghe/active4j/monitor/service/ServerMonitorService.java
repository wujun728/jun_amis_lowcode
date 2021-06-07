package com.zhonghe.active4j.monitor.service;

import com.zhonghe.active4j.monitor.model.ServerInfoModel;

/**
 * @title MonitorService.java
 * @description 
		  服务器监控
 * @time  2019年12月3日 下午4:03:33
 * @author 麻木神
 * @version 1.0
*/
public interface ServerMonitorService {

	
	/**
	 * 
	 * @description
	 *  	获取服务器信息
	 * @params
	 * @return ServerInfoModel
	 * @author 麻木神
	 * @time 2019年12月3日 下午4:39:42
	 */
	public ServerInfoModel getServer();
}
