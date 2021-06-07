package com.zhonghe.active4j.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhonghe.active4j.system.entity.SysMenuEntity;
import com.zhonghe.active4j.system.entity.SysRoleEntity;
import com.zhonghe.active4j.system.entity.SysUserEntity;

public interface SysUserDao extends BaseMapper<SysUserEntity>{

	/**
	 * 
	 * @description
	 *  	根据用户id查询角色列表
	 * @params
	 *      userId 用户id
	 * @return List<SysRoleEntity>
	 * @author guyp
	 * @time 2020年1月16日 下午2:19:15
	 */
	public List<SysRoleEntity> findRolesByUserId(@Param("userId") String userId);

	/**
	 * 
	 * @description
	 *  	根据用户id查询目录
	 * @params
	 *      userId 用户id
	 * @return List<SysMenuEntity>
	 * @author guyp
	 * @time 2020年1月16日 下午2:27:59
	 */
	public List<SysMenuEntity> findMenuByUserId(@Param("userId") String userId);
}
