package com.zhonghe.active4j.monitor.service;

import org.apache.shiro.session.Session;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.monitor.entity.OnlineSessionEntity;

/**
 * @title OnlineSessionService.java
 * @description 
 * @time  2019年12月5日 下午10:05:04
 * @author 麻木神
 * @version 1.0
*/
public interface OnlineSessionService extends IService<OnlineSessionEntity>{

	/**
	 * @description
	 *  	新增online session
	 * @params
	 *      shiro session
	 * @return void
	 * @author 麻木神
	 * @time 2019年12月6日 下午12:02:31
	 */
	public void saveOnlineSession(Session session);
	
	
}
