package com.zhonghe.active4j.core.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.DelegatingSession;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.crazycake.shiro.RedisSessionDAO;

import com.zhonghe.active4j.core.util.ApplicationContextUtil;
import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.monitor.entity.OnlineSessionEntity;
import com.zhonghe.active4j.monitor.service.OnlineSessionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @title MyWebSessionManager.java
 * @description 重写websessionmanager
 * @time 2019年12月6日 下午4:01:00
 * @author 麻木神
 * @version 1.0
 */
@Slf4j
public class MyWebSessionManager extends DefaultSessionManager implements WebSessionManager {
	
	
	/**
	 * 重写验证session功能，保留之前定时校验session的功能
	 * 	加入统计在线用户，并更新在线用户状态功能
	 *  集群环境下，不能使用默认的sessiondao
	 */
	@Override
	public void validateSessions() {
		/**
		 * 重写了getActiveSessions方法  获取不到sessions了
		 */
		super.validateSessions();
		
		//新增功能，不需要时可以去除
		validateOnlineSession();
		
	}
	
	/**
	 * 覆盖此方法，因为session使用redis管理，且redis在获取activesession使用keys查询，尽量避免redis中使用keys
	 * 	因为sesion的过期使用了redis的过期功能，所以对于过期session这里原本也获取不到
	 */
	@Override
	protected Collection<Session> getActiveSessions() {
		
		if(this.sessionDAO instanceof RedisSessionDAO) {
			log.info("系统采用redis管理session,建议不能够使用Keys返回session");
			return Collections.<Session>emptySet();
		}else {
			return super.getActiveSessions();
		}
		
	}


	/**
	 * @description
	 *  	加入新增校验数据库中在线session验证功能
	 * @return void
	 * @author 麻木神
	 * @time 2019年12月9日 上午10:01:15
	 */
	private void validateOnlineSession() {
		/**
		 * 查询数据库中所有的active session
		 * 	因为需要全表查询，如果在线用户数据过大，不建议使用
		 */
		OnlineSessionService onlineSessionService = ApplicationContextUtil.getContext().getBean(OnlineSessionService.class);
		List<OnlineSessionEntity> lstOnlineSessions = onlineSessionService.list();
		
		if(null != lstOnlineSessions && lstOnlineSessions.size() > 0) {
			for(OnlineSessionEntity onlineSession : lstOnlineSessions) {
				//根据onlinesession获取session
				try {
					Session session = this.sessionDAO.readSession(onlineSession.getSessionId());
					if(null != session) {
						//存在session
						
						if(session instanceof ValidatingSession) {
							((ValidatingSession) session).validate();
						}
						
						//更新session时间
						onlineSession.setLastTime(session.getLastAccessTime());
						onlineSession.setUpdateDate(DateUtils.getNow());
						onlineSession.setUpdateName("system");
						onlineSessionService.saveOrUpdate(onlineSession);
					}else {
						//不存在session
						onlineSessionService.removeById(onlineSession.getId());
					}
				}catch(Exception e) {
					//或者取不到session，或者session已经过期，删除session
					onlineSessionService.removeById(onlineSession.getId());
					log.warn(e.getMessage(), e);
				}
			}
		}
	}
	
	
	

	private Cookie sessionIdCookie;
	private boolean sessionIdCookieEnabled;
	private boolean sessionIdUrlRewritingEnabled;

	public MyWebSessionManager() {
		Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
		cookie.setHttpOnly(true); // more secure, protects against XSS attacks
		this.sessionIdCookie = cookie;
		this.sessionIdCookieEnabled = true;
		this.sessionIdUrlRewritingEnabled = true;
	}

	public Cookie getSessionIdCookie() {
		return sessionIdCookie;
	}

	@SuppressWarnings({ "UnusedDeclaration" })
	public void setSessionIdCookie(Cookie sessionIdCookie) {
		this.sessionIdCookie = sessionIdCookie;
	}

	public boolean isSessionIdCookieEnabled() {
		return sessionIdCookieEnabled;
	}

	@SuppressWarnings({ "UnusedDeclaration" })
	public void setSessionIdCookieEnabled(boolean sessionIdCookieEnabled) {
		this.sessionIdCookieEnabled = sessionIdCookieEnabled;
	}

	public boolean isSessionIdUrlRewritingEnabled() {
		return sessionIdUrlRewritingEnabled;
	}

	@SuppressWarnings({ "UnusedDeclaration" })
	public void setSessionIdUrlRewritingEnabled(boolean sessionIdUrlRewritingEnabled) {
		this.sessionIdUrlRewritingEnabled = sessionIdUrlRewritingEnabled;
	}

	private void storeSessionId(Serializable currentId, HttpServletRequest request, HttpServletResponse response) {
		if (currentId == null) {
			String msg = "sessionId cannot be null when persisting for subsequent requests.";
			throw new IllegalArgumentException(msg);
		}
		Cookie template = getSessionIdCookie();
		Cookie cookie = new SimpleCookie(template);
		String idString = currentId.toString();
		cookie.setValue(idString);
		cookie.saveTo(request, response);
		log.trace("Set session ID cookie for session with id {}", idString);
	}

	private void removeSessionIdCookie(HttpServletRequest request, HttpServletResponse response) {
		getSessionIdCookie().removeFrom(request, response);
	}

	private String getSessionIdCookieValue(ServletRequest request, ServletResponse response) {
		if (!isSessionIdCookieEnabled()) {
			log.debug("Session ID cookie is disabled - session id will not be acquired from a request cookie.");
			return null;
		}
		if (!(request instanceof HttpServletRequest)) {
			log.debug("Current request is not an HttpServletRequest - cannot get session ID cookie.  Returning null.");
			return null;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		return getSessionIdCookie().readValue(httpRequest, WebUtils.toHttp(response));
	}

	private Serializable getReferencedSessionId(ServletRequest request, ServletResponse response) {

		String id = getSessionIdCookieValue(request, response);
		if (id != null) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
		} else {
			// not in a cookie, or cookie is disabled - try the request URI as a fallback
			// (i.e. due to URL rewriting):

			// try the URI path segment parameters first:
			id = getUriPathSegmentParamValue(request, ShiroHttpSession.DEFAULT_SESSION_ID_NAME);

			if (id == null) {
				// not a URI path segment parameter, try the query parameters:
				String name = getSessionIdName();
				id = request.getParameter(name);
				if (id == null) {
					// try lowercase:
					id = request.getParameter(name.toLowerCase());
				}
			}
			if (id != null) {
				request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
			}
		}
		if (id != null) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
			// automatically mark it valid here. If it is invalid, the
			// onUnknownSession method below will be invoked and we'll remove the attribute
			// at that time.
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
		}

		// always set rewrite flag - SHIRO-361
		request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());

		return id;
	}

	// SHIRO-351
	// also see
	// http://cdivilly.wordpress.com/2011/04/22/java-servlets-uri-parameters/
	// since 1.2.2
	private String getUriPathSegmentParamValue(ServletRequest servletRequest, String paramName) {

		if (!(servletRequest instanceof HttpServletRequest)) {
			return null;
		}
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String uri = request.getRequestURI();
		if (uri == null) {
			return null;
		}

		int queryStartIndex = uri.indexOf('?');
		if (queryStartIndex >= 0) { // get rid of the query string
			uri = uri.substring(0, queryStartIndex);
		}

		int index = uri.indexOf(';'); // now check for path segment parameters:
		if (index < 0) {
			// no path segment params - return:
			return null;
		}

		// there are path segment params, let's get the last one that may exist:

		final String TOKEN = paramName + "=";

		uri = uri.substring(index + 1); // uri now contains only the path segment params

		// we only care about the last JSESSIONID param:
		index = uri.lastIndexOf(TOKEN);
		if (index < 0) {
			// no segment param:
			return null;
		}

		uri = uri.substring(index + TOKEN.length());

		index = uri.indexOf(';'); // strip off any remaining segment params:
		if (index >= 0) {
			uri = uri.substring(0, index);
		}

		return uri; // what remains is the value
	}

	// since 1.2.1
	private String getSessionIdName() {
		String name = this.sessionIdCookie != null ? this.sessionIdCookie.getName() : null;
		if (name == null) {
			name = ShiroHttpSession.DEFAULT_SESSION_ID_NAME;
		}
		return name;
	}

	protected Session createExposedSession(Session session, SessionContext context) {
		if (!WebUtils.isWeb(context)) {
			return super.createExposedSession(session, context);
		}
		ServletRequest request = WebUtils.getRequest(context);
		ServletResponse response = WebUtils.getResponse(context);
		SessionKey key = new WebSessionKey(session.getId(), request, response);
		return new DelegatingSession(this, key);
	}

	protected Session createExposedSession(Session session, SessionKey key) {
		if (!WebUtils.isWeb(key)) {
			return super.createExposedSession(session, key);
		}

		ServletRequest request = WebUtils.getRequest(key);
		ServletResponse response = WebUtils.getResponse(key);
		SessionKey sessionKey = new WebSessionKey(session.getId(), request, response);
		return new DelegatingSession(this, sessionKey);
	}

	/**
	 * Stores the Session's ID, usually as a Cookie, to associate with future
	 * requests.
	 *
	 * @param session the session that was just {@link #createSession created}.
	 */
	@Override
	protected void onStart(Session session, SessionContext context) {
		super.onStart(session, context);

		if (!WebUtils.isHttp(context)) {
			log.debug("SessionContext argument is not HTTP compatible or does not have an HTTP request/response " + "pair. No session ID cookie will be set.");
			return;

		}
		HttpServletRequest request = WebUtils.getHttpRequest(context);
		HttpServletResponse response = WebUtils.getHttpResponse(context);

		if (isSessionIdCookieEnabled()) {
			Serializable sessionId = session.getId();
			storeSessionId(sessionId, request, response);
		} else {
			log.debug("Session ID cookie is disabled.  No cookie has been set for new session with id {}", session.getId());
		}

		request.removeAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE);
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_IS_NEW, Boolean.TRUE);
	}

	@Override
	public Serializable getSessionId(SessionKey key) {
		Serializable id = super.getSessionId(key);
		if (id == null && WebUtils.isWeb(key)) {
			ServletRequest request = WebUtils.getRequest(key);
			ServletResponse response = WebUtils.getResponse(key);
			id = getSessionId(request, response);
		}
		return id;
	}

	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		return getReferencedSessionId(request, response);
	}

	@Override
	protected void onExpiration(Session s, ExpiredSessionException ese, SessionKey key) {
		super.onExpiration(s, ese, key);
		onInvalidation(key);
	}

	@Override
	protected void onInvalidation(Session session, InvalidSessionException ise, SessionKey key) {
		super.onInvalidation(session, ise, key);
		onInvalidation(key);
	}

	private void onInvalidation(SessionKey key) {
		ServletRequest request = WebUtils.getRequest(key);
		if (request != null) {
			request.removeAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID);
		}
		if (WebUtils.isHttp(key)) {
			log.debug("Referenced session was invalid.  Removing session ID cookie.");
			removeSessionIdCookie(WebUtils.getHttpRequest(key), WebUtils.getHttpResponse(key));
		} else {
			log.debug("SessionKey argument is not HTTP compatible or does not have an HTTP request/response " + "pair. Session ID cookie will not be removed due to invalidated session.");
		}
	}

	@Override
	protected void onStop(Session session, SessionKey key) {
		super.onStop(session, key);
		if (WebUtils.isHttp(key)) {
			HttpServletRequest request = WebUtils.getHttpRequest(key);
			HttpServletResponse response = WebUtils.getHttpResponse(key);
			log.debug("Session has been stopped (subject logout or explicit stop).  Removing session ID cookie.");
			removeSessionIdCookie(request, response);
		} else {
			log.debug("SessionKey argument is not HTTP compatible or does not have an HTTP request/response " + "pair. Session ID cookie will not be removed due to stopped session.");
		}
	}

	/**
	 * This is a native session manager implementation, so this method returns
	 * {@code false} always.
	 *
	 * @return {@code false} always
	 * @since 1.2
	 */
	public boolean isServletContainerSessions() {
		return false;
	}
}
