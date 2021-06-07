package com.zhonghe.active4j.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.func.message.entity.SysUserMessageEntity;
import com.zhonghe.active4j.func.message.service.SysUserMessageService;
import com.zhonghe.active4j.monitor.entity.OnlineSessionEntity;
import com.zhonghe.active4j.monitor.service.OnlineSessionService;
import com.zhonghe.active4j.system.dao.SysUserDao;
import com.zhonghe.active4j.system.entity.SysMenuEntity;
import com.zhonghe.active4j.system.entity.SysRoleEntity;
import com.zhonghe.active4j.system.entity.SysUserEntity;
import com.zhonghe.active4j.system.entity.SysUserRoleEntity;
import com.zhonghe.active4j.system.model.ActiveUser;
import com.zhonghe.active4j.system.model.MenuModel;
import com.zhonghe.active4j.system.service.SysRoleService;
import com.zhonghe.active4j.system.service.SysUserRoleService;
import com.zhonghe.active4j.system.service.SysUserService;
import com.zhonghe.active4j.system.util.SystemUtils;


/**
 * 用户管理service类
 * @author teli_
 *
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private OnlineSessionService onlineSessionService;
	
	@Autowired
	private SysUserMessageService sysUserMessageService;
	
	
	
	/**
	 * 保存用户， 角色保存
	 */
	public void saveUser(SysUserEntity user, String roleIds) {
		
		super.save(user);
		
		if(StringUtils.isNotEmpty(roleIds)) {
			String[] roles = roleIds.split(",");
			for(String roleId : roles) {
				SysUserRoleEntity userRole = new SysUserRoleEntity();
				userRole.setRoleId(roleId);
				userRole.setUserId(user.getId());
				sysUserRoleService.save(userRole);
			}
		}
	}
	
	/**
	 * 编辑用户， 角色修改
	 * @param user
	 * @param roleIds
	 */
	public void saveOrUpdateUser(SysUserEntity user, String roleIds) {
		
		//先删除之前的角色
		QueryWrapper<SysUserRoleEntity> queryWrapper = new QueryWrapper<SysUserRoleEntity>();
		queryWrapper.eq("USER_ID", user.getId());
		sysUserRoleService.remove(queryWrapper);
		
		//保存用户角色
		if(StringUtils.isNotEmpty(roleIds)) {
			String[] roles = roleIds.split(",");
			for(String roleId : roles) {
				SysUserRoleEntity userRole = new SysUserRoleEntity();
				userRole.setRoleId(roleId);
				userRole.setUserId(user.getId());
				sysUserRoleService.save(userRole);
			}
		}
		
		//修改用户信息
		super.saveOrUpdate(user);
	}
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(SysUserEntity user) {
		//先删除角色
		QueryWrapper<SysUserRoleEntity> queryRole = new QueryWrapper<SysUserRoleEntity>();
		queryRole.eq("USER_ID", user.getId());
		sysUserRoleService.remove(queryRole);
		
		//删除在线用户
		QueryWrapper<OnlineSessionEntity> querySession = new QueryWrapper<OnlineSessionEntity>();
		querySession.eq("USER_ID", user.getId());
		onlineSessionService.remove(querySession);
		
		//删除系统消息
		QueryWrapper<SysUserMessageEntity> queryMsg = new QueryWrapper<SysUserMessageEntity>();
		queryMsg.eq("USER_ID", user.getId());
		sysUserMessageService.remove(queryMsg);
		
		//删除用户
		super.removeById(user.getId());
	}
	
	
	/**
	 * 获取用户所属的角色
	 * @param user
	 * @return
	 */
	public List<SysRoleEntity> getUserRoles(SysUserEntity user){
		List<SysRoleEntity> lstRoles = new ArrayList<SysRoleEntity>();
		QueryWrapper<SysUserRoleEntity> queryWrapper = new QueryWrapper<SysUserRoleEntity>();
		queryWrapper.eq("USER_ID", user.getId());
		List<SysUserRoleEntity> lstUserRoles = sysUserRoleService.list(queryWrapper);
		if(null != lstUserRoles && lstUserRoles.size() > 0) {
			for(SysUserRoleEntity userRole : lstUserRoles) {
				lstRoles.add(sysRoleService.getById(userRole.getRoleId()));
			}
		}
		
		return lstRoles;
		
	}
	
	
	/**
	 * 根据用户名取得用户
	 * @param userName
	 * @return
	 */
	public SysUserEntity getUserByUseName(String userName) {
		QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<SysUserEntity>();
		queryWrapper.eq("USER_NAME", userName);
		List<SysUserEntity> lstUserRoles = this.list(queryWrapper);
		
		if(null != lstUserRoles && lstUserRoles.size() > 0) {
			return lstUserRoles.get(0);
		}
		
		return null;
	}
	
	/**
	 * 根据用户信息  组装shiro session用户
	 * @param user
	 * @return
	 */
	public ActiveUser getActiveUserByUser(SysUserEntity user) {
		ActiveUser activeUser = new ActiveUser();
		activeUser.setId(user.getId());
		activeUser.setRealName(user.getRealName());
		activeUser.setUserName(user.getUserName());
		activeUser.setAvatar(user.getHeadImgUrl());
		activeUser.setDeptName(SystemUtils.getDeptNameById(user.getDeptId()));
		
		
		//可见菜单处理
		//查询用户角色
		List<SysRoleEntity> lstRoles = this.baseMapper.findRolesByUserId(user.getId());
		//角色可见菜单
		List<SysMenuEntity> lstMenus = this.baseMapper.findMenuByUserId(user.getId());
		//复制一份
		List<SysMenuEntity> lstTmp = new ArrayList<SysMenuEntity>(lstMenus);
		//拼接角色模型
		//用户页面菜单显示集合
		activeUser.setMenus(getMenus(lstMenus));
		//赋值权限
		activeUser.setPermissions(lstTmp);
		activeUser.setRoles(lstRoles);
		return activeUser;
	}
	
	/**
	 * 根据角色可见菜单，组装系统菜单栏
	 * @param lstMenus
	 * @return
	 */
	private List<MenuModel> getMenus(List<SysMenuEntity> lstMenus) {
		List<MenuModel> lstModels = new ArrayList<MenuModel>();
		if(null != lstMenus && lstMenus.size() > 0) {
			//移除菜单中的按钮
			lstMenus.removeIf(m -> StringUtils.equals("1", m.getType()));
			//取最顶层菜单
			if(null != lstMenus && lstMenus.size() > 0) {
				Iterator<SysMenuEntity> itMenus = lstMenus.iterator();
				while(itMenus.hasNext()) {
					SysMenuEntity m = itMenus.next();
					if(StringUtils.isEmpty(m.getParentId())) {
						lstModels.add(getMenuModel(m));
						itMenus.remove();
					}
				}
			}
		}
		if(lstModels.size() > 0) {
			//排序
			Collections.sort(lstModels, (m1, m2)-> m1.getOrderNo() - m2.getOrderNo());
			//取子菜单
			for(MenuModel model : lstModels) {
				List<MenuModel> firstLst = new ArrayList<MenuModel>();
				Iterator<SysMenuEntity> itMenus = lstMenus.iterator();
				while(itMenus.hasNext()) {
					SysMenuEntity m = itMenus.next();
					if(StringUtils.equals(m.getParentId(), model.getId())) {
						firstLst.add(getMenuModel(m));
						itMenus.remove();
					}
				}
				if(firstLst.size() > 0) {
					//排序
					Collections.sort(firstLst, (m1, m2)-> m1.getOrderNo() - m2.getOrderNo());
					
					model.setHasChildren(true);
					model.setChildren(firstLst);
					
					//取子菜单
					for(MenuModel secondM : firstLst) {
						List<MenuModel> secondLst = new ArrayList<MenuModel>();
						
						Iterator<SysMenuEntity> itMenus2 = lstMenus.iterator();
						while(itMenus2.hasNext()) {
							SysMenuEntity m = itMenus2.next();
							if(StringUtils.equals(m.getParentId(), secondM.getId())) {
								secondLst.add(getMenuModel(m));
								itMenus2.remove();
							}
						}
						if(secondLst.size() > 0) {
							Collections.sort(secondLst, (m1, m2)-> m1.getOrderNo() - m2.getOrderNo());
							secondM.setHasChildren(true);
							secondM.setChildren(secondLst);
						}
					}
					
					
				}
			}
			
		}
		
		
		return lstModels;
	}
	
	
	private MenuModel getMenuModel(SysMenuEntity m) {
		MenuModel menu = new MenuModel();
		menu.setId(m.getId());
		menu.setTitle(m.getName());
		menu.setOrderNo(m.getOrderNo());
		menu.setIcon(m.getIcon());
		menu.setUrl(m.getUrl());
		
		return menu;
	}
}
