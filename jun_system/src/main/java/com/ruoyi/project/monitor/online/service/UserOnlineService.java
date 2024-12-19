package com.ruoyi.project.monitor.online.service;

import java.io.Serializable;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.constant.ShiroConstants;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.RequestUtil;
import com.ruoyi.common.utils.db.BatchSql;
import com.ruoyi.framework.shiro.session.OnlineSessionDAO;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.CommonService;
import com.ruoyi.project.monitor.online.domain.OnlineSession.OnlineStatus;
import com.ruoyi.project.monitor.online.domain.UserOnline;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 在线用户 服务层处理
 * @author ruoyi
 */
@Service
public class UserOnlineService extends CommonService {

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @Autowired
    private EhCacheManager ehCacheManager;

    /**
     * 查询在线用户（分页查询）
     * @param
     * @return
     */
    public TableDataInfo selectUserOnlineList(HttpServletRequest request) {
    	String ipaddr = RequestUtil.getValue(request, "ipaddr");
    	String login_name = RequestUtil.getValue(request, "login_name");

    	List<String> paramList = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer("select sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, start_timestamp, last_access_time, expire_time, " +
				 " if((status = 'on_line'), '在线', '离线') status_name from sys_user_online a where 1 = 1 ");

    	if(StrUtil.isNotBlank(ipaddr)) {
    		sql.append(" and a.ipaddr like concat('%', ?, '%') ");
    		paramList.add(ipaddr);
    	}
    	if(StrUtil.isNotBlank(login_name)) {
    		sql.append(" and a.login_name like concat('%', ?, '%') ");
    		paramList.add(login_name);
    	}

		//拼接排序语句
		this.addOrderBySql(request, sql, "a.start_timestamp");

        return this.getRespTableDataInfo(request, sql.toString(), paramList, true);
    }

    /**
     * 通过会话序号查询信息
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public UserOnline selectOnlineById(String sessionId) {
    	String sql = "select sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, start_timestamp, last_access_time, expire_time " +
		 	 	 "  from sys_user_online a where sessionId = ?";
    	UserOnline userOnline = db.queryForObject(sql, new Object[]{sessionId}, UserOnline.class);

    	if(userOnline != null) {
    		if("on_line".equals(userOnline.getOnlineStatus())) {
    			userOnline.setStatus(OnlineStatus.on_line);
    		}
    		else {
    			userOnline.setStatus(OnlineStatus.off_line);
    		}
    	}
        return userOnline;
    }

    /**
     * 通过会话序号删除信息
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteOnlineById(String sessionId) {
    	String sql = "delete from sys_user_online where sessionId = ?";
    	db.execute(sql, new Object[]{sessionId});
    }

    /**
     * 通过会话序号删除信息
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    public void batchDeleteOnline(List<String> sessions) {
    	BatchSql batchSql = new BatchSql();
    	String sql = "delete from sys_user_online where sessionId = ?";
        for (String sessionId : sessions) {
        	batchSql.addBatch(sql, new Object[]{sessionId});
        }
        db.doInTransaction(batchSql);
    }

    /**
     * 保存会话信息
     * @param online 会话信息
     */
    public void saveOnline(Map<String, Object> online) {
        String sql = "replace into sys_user_online(sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, "+
        		"start_timestamp, last_access_time, expire_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.execute(sql, new Object[]{MapUtil.getStr(online, "sessionId"), MapUtil.getStr(online, "login_name"), MapUtil.getStr(online, "dept_name"),
        		MapUtil.getStr(online, "ipaddr"), MapUtil.getStr(online, "login_location"), MapUtil.getStr(online, "browser"),
        		MapUtil.getStr(online, "os"), MapUtil.getStr(online, "status"), MapUtil.getStr(online, "start_timestamp"),
        		MapUtil.getStr(online, "last_access_time"), MapUtil.getStr(online, "expire_time")});
    }

    /**
     * 保存会话信息
     * @param online 会话信息
     */
    public void saveOnline(UserOnline online) {
        String sql = "replace into sys_user_online(sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, "+
        		"start_timestamp, last_access_time, expire_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.execute(sql, new Object[]{online.getSessionId(), online.getLoginName(), online.getDeptName(), online.getIpaddr(),
        		online.getLoginLocation(), online.getBrowser(), online.getOs(), online.getStatus().toString(),
        		DateUtil.formatDateTime(online.getStartTimestamp()), DateUtil.formatDateTime(online.getLastAccessTime()),
        		online.getExpireTime()});
    }

    /**
     * 强退用户
     * @param sessionId 会话ID
     */
    public void forceLogout(String sessionId) {
        Session session = onlineSessionDAO.readSession(sessionId);
        if (session == null) {
            return;
        }
        session.setTimeout(1000);
        deleteOnlineById(sessionId);
    }

    /**
     * 清理用户缓存
     * @param loginName 登录名称
     * @param sessionId 会话ID
     */
    public void removeUserCache(String loginName, String sessionId) {
        Cache<String, Deque<Serializable>> cache = ehCacheManager.getCache(ShiroConstants.SYS_USERCACHE);
        Deque<Serializable> deque = cache.get(loginName);
        if (CollUtil.isEmpty(deque) || deque.size() == 0) {
            return;
        }
        deque.remove(sessionId);
    }

    /**
     * 查询会话集合
     * @param expiredDate 超时时间
     */
    public List<UserOnline> selectOnlineByExpired(Date expiredDate) {
    	String lastAccessTime = DateUtil.format(expiredDate, DatePattern.NORM_DATETIME_PATTERN);
        String sql = "select sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, start_timestamp, "+
        			 "		 last_access_time, expire_time, if((status = 'on_line'), '在线', '离线') status_name " +
        			 "  from sys_user_online a " +
        			 " where a.last_access_time <= ? order by a.last_access_time";
        return db.queryForList(sql, new Object[]{lastAccessTime}, UserOnline.class);
    }
}