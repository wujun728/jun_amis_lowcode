package com.jun.plugin.modules.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.exception.HtException;
import com.jun.plugin.common.util.PasswordHelper;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.sys.mapper.UserMapper;
import com.jun.plugin.modules.sys.mapper.UserRoleMapper;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.model.SysUserRole;
import com.jun.plugin.modules.sys.service.UserService;
import com.jun.plugin.modules.sys.vo.UserOnlineVo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private SessionManager sessionManager;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public SysUser getUserByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
		queryWrapper.eq("username", username);
		return userMapper.selectOne(queryWrapper);
	}

	@Override
	public int register(SysUser user) {
		return userMapper.insert(user);
	}

	@Override
	public void updateLastLoginTimeByUser(SysUser sysUser) {
		if (sysUser != null) {
			sysUser.setLastLoginTime(new Date());
			userMapper.updateById(sysUser);
		}
	}

	@Override
	public List<SysUser> listUsers(SysUser user) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
		if (StringUtils.isNotEmpty(user.getUsername())) {
			queryWrapper.like("username", user.getUsername());
		}
		if (StringUtils.isNotEmpty(user.getEmail())) {
			queryWrapper.like("email", user.getEmail());
		}
		if (StringUtils.isNotEmpty(user.getPhone())) {
			queryWrapper.like("phone", user.getPhone());
		}
		return userMapper.selectList(queryWrapper);
	}

	@Override
	public SysUser getUserByUserId(Long userId) {
		return userMapper.selectById(userId);
	}

	@Override
	public int updateUserByUserId(SysUser sysUser) {
		sysUser.setUpdateTime(new Date());
		return userMapper.updateById(sysUser);
	}

	@Override
	public int updateUserStatusByUserIds(List<String> userIds, Integer status) {
		if (StringUtils.isEmpty(userIds)) {
			return CoreConst.RESULT_FAIL_CODE;
		}
		if (status == null) {
			return CoreConst.RESULT_FAIL_CODE;
		}
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("userIds", userIds);
		params.put("status", status);
		return userMapper.updateUserStatusByUserIds(params);
	}

	@Override
	public ResponseVo<?> addAssignRole(Long userId, Long[] roleIds) {
		try {
			if (userId == null) {
				return ResultUtil.error("分配角色失败");
			}
			QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
			queryWrapper.eq("user_id", userId);
			userRoleMapper.delete(queryWrapper);
			// 清空角色分配
			if (StringUtils.isEmpty(roleIds)) {
				return ResultUtil.success("分配角色成功");
			}
			List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
			for (Long roleId : roleIds) {
				SysUserRole userRole = new SysUserRole();
				userRole.setUserRoleId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRoleList.add(userRole);
				userRoleMapper.insert(userRole);
			}
			return ResultUtil.success("分配角色成功");
		} catch (Exception e) {
			return ResultUtil.error("分配角色失败");
		}
	}

	@Override
	public String importUser(List<SysUser> userList, boolean updateSupport, String operName) {
		if (StringUtils.isEmpty(userList) || userList.size() == 0) {
			throw new HtException("导入用户数据不能为空！");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		for (SysUser user : userList) {
			try {
				// 验证是否存在这个用户
				SysUser u = this.getUserByUsername(user.getUsername());
				if (StringUtils.isNull(u)) {
					if(StringUtils.isEmpty(user.getPassword())) {
						user.setPassword("123456");
					}
					PasswordHelper.encryptPassword(user);
					userMapper.insert(user);
					successNum++;
					successMsg.append("<br/>" + successNum + "、账号 " + user.getUsername() + " 导入成功");
				} else if (updateSupport) {
					userMapper.updateById(user);
					successNum++;
					successMsg.append("<br/>" + successNum + "、账号 " + user.getUsername() + " 更新成功");
				} else {
					failureNum++;
					failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUsername() + " 已存在");
				}
			} catch (Exception e) {
				failureNum++;
				String msg = "<br/>" + failureNum + "、账号 " + user.getUsername() + " 导入失败：";
				failureMsg.append(msg + e.getMessage());
			}
		}
		if (failureNum > 0) {
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new HtException(failureMsg.toString());
		} else {
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
		}
		return successMsg.toString();
	}
	

    @Override
    public List<UserOnlineVo> selectOnlineUsers(UserOnlineVo userVo) {
        // 因为我们是用redis实现了shiro的session的Dao,而且是采用了shiro+redis这个插件
        // 所以从spring容器中获取redisSessionDAO
        // 来获取session列表.
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        Iterator<Session> it = sessions.iterator();
        List<UserOnlineVo> onlineUserList = new ArrayList<UserOnlineVo>();
        // 遍历session
        while (it.hasNext()) {
            // 这是shiro已经存入session的
            // 现在直接取就是了
            Session session = it.next();
            //标记为已提出的不加入在线列表
            if(session.getAttribute("kickout") != null) {
                continue;
            }
            UserOnlineVo onlineUser = getSessionBo(session);
            if(onlineUser!=null){
                /*用户名搜索*/
                if(StringUtils.isNotBlank(userVo.getUsername())){
                    if(onlineUser.getUsername().contains(userVo.getUsername()) ){
                        onlineUserList.add(onlineUser);
                    }
                }else{
                    onlineUserList.add(onlineUser);
                }
            }
        }
        return onlineUserList;
    }
    

    private UserOnlineVo getSessionBo(Session session){
        //获取session登录信息。
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if(null == obj){
            return null;
        }
        //确保是 SimplePrincipalCollection对象。
        if(obj instanceof SimplePrincipalCollection){
            SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
            obj = spc.getPrimaryPrincipal();
            if(null != obj && obj instanceof SysUser){
            	SysUser user = (SysUser) obj;
                //存储session + user 综合信息
                UserOnlineVo userBo = new UserOnlineVo();
                //最后一次和系统交互的时间
                userBo.setLastAccess(session.getLastAccessTime());
                //主机的ip地址
                userBo.setHost(user.getLoginIpAddress());
                //session ID
                userBo.setSessionId(session.getId().toString());
                //最后登录时间
                userBo.setLastLoginTime(user.getLastLoginTime());
                //回话到期 ttl(ms)
                userBo.setTimeout(session.getTimeout());
                //session创建时间
                userBo.setStartTime(session.getStartTimestamp());
                //是否踢出
                userBo.setSessionStatus(false);
                /*用户名*/
                userBo.setUsername(user.getUsername());
                return userBo;
            }
        }
        return null;
    }

    @Override
    public void kickout(Serializable sessionId, String username) {
        getSessionBysessionId(sessionId).setAttribute("kickout", true);
        //读取缓存,找到并从队列中移除
        Cache<String, Deque<Serializable>> cache = redisCacheManager.getCache(redisCacheManager.getKeyPrefix()+username);
        Deque<Serializable> deques = cache.get(username);
        for(Serializable deque : deques){
            if(sessionId.equals(deque)){
                deques.remove(deque);
                break;
            }
        }
        cache.put(username,deques);
    }
    

    private Session getSessionBysessionId(Serializable sessionId){
        Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(sessionId));
        return kickoutSession;
    }
    

}
