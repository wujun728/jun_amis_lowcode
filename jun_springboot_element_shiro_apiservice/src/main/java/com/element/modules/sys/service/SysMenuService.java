package com.element.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.element.modules.sys.entity.SysMenuEntity;
import com.element.modules.sys.entity.SysUserEntity;
import com.element.modules.sys.entity.vo.RouterVo;

import java.util.List;
import java.util.Set;


/**
 * 菜单管理
 */
public interface SysMenuService extends IService<SysMenuEntity> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> listMenuByParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> listMenuNotButton();
	
	/**
	 * 删除
	 */
	void delete(Long menuId);

	/**
	 * 获取用户菜单权限
	 */
    Set<String> listMenuPermission(SysUserEntity user);

	/**
	 * 获取用户菜单树
	 */
    List<SysMenuEntity> listMenuTreeByUserId(Long userId);

	/**
	 * 构建用户菜单路由
	 */
	List<RouterVo> buildMenus(List<SysMenuEntity> menus);
}
