package com.zhonghe.active4j.monitor.service.impl;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.common.constant.GlobalConstant;
import com.zhonghe.active4j.core.session.GlobalSessionConstant;
import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.monitor.dao.OnlineSessionDao;
import com.zhonghe.active4j.monitor.entity.OnlineSessionEntity;
import com.zhonghe.active4j.monitor.service.OnlineSessionService;
import com.zhonghe.active4j.system.model.ActiveUser;

/**
 * @title OnlineSessionServiceImpl.java
 * @description 
 * @time  2019年12月5日 下午10:06:04
 * @author 麻木神
 * @version 1.0
*/

@Service("onlineSessionService")
@Transactional
public class OnlineSessionServiceImpl extends ServiceImpl<OnlineSessionDao, OnlineSessionEntity> implements OnlineSessionService{

	/**
	 * @description
	 *  	新增online session
	 * @params
	 *      shiro session
	 * @return void
	 * @author 麻木神
	 * @time 2019年12月6日 下午12:02:31
	 */
	public void saveOnlineSession(Session session) {
		OnlineSessionEntity onlineSession = getOnlineSession(session);
		
		this.save(onlineSession);
	}
	
	/**
	 * @description
	 *  	保存数据库  
	 * @return OnlineSessionEntity
	 * @author 麻木神
	 * @time 2019年12月6日 上午10:22:29
	 */
	private OnlineSessionEntity getOnlineSession(Session session) {
		OnlineSessionEntity onlineSession = new OnlineSessionEntity();
		
		onlineSession.setBrowser((String)session.getAttribute(GlobalSessionConstant.SESSION_BROWSER));
		onlineSession.setCreateDate(DateUtils.getNow());
		onlineSession.setCreateName(GlobalConstant.Default_Create_Name);
		onlineSession.setHost(session.getHost());
		onlineSession.setLastTime(session.getLastAccessTime());
		onlineSession.setOs((String)session.getAttribute(GlobalSessionConstant.SESSION_OS));
		onlineSession.setSessionId(session.getId().toString());
		onlineSession.setBeginTime(session.getStartTimestamp());
		onlineSession.setStatus(OnlineSessionEntity.Status_Online);
		//关于用户信息的赋值
		ActiveUser user = (ActiveUser)session.getAttribute(GlobalSessionConstant.SESSION_USER);
		if(null != user) {
			onlineSession.setUserId(user.getId());
			onlineSession.setUserName(user.getUserName());
			onlineSession.setAvatar(user.getAvatar());
			onlineSession.setDeptName(user.getDeptName());
			onlineSession.setRealName(user.getRealName());
		}
		return onlineSession;
	}
	
}
