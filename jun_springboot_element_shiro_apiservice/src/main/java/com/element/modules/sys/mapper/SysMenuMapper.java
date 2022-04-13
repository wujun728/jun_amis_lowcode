package com.element.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
	
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
	 * 查询用户拥有的菜单权限
	 */
    List<String> listMenuPermsByUserId(Long userId);

	/**
	 * 查询所有的菜单树
	 */
    List<SysMenuEntity> listMenuTreeAll();

	/**
	 * 查询用户的菜单树
	 */
	List<SysMenuEntity> listMenuTreeByUserId(Long userId);
}
