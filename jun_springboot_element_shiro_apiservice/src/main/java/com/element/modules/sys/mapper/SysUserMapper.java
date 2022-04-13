package com.element.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> listPermsByUserId(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> listMenuIdByCreateUserId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity getByUserName(String username);

}
