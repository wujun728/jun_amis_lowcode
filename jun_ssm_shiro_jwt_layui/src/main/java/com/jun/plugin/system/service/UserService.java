package com.jun.plugin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.User;
import com.jun.plugin.system.vo.UserVo;

import java.util.List;

/**
 * ClassName: UserService Description: layui date: 2020/4/14 19:50
 *
 * 
 * 
 * @since JDK 1.8
 */
public interface UserService extends IService<User> {

	/**
	 * 根据用户名查询用户登陆信息
	 */
	public User queryUserByLoginName(String loingname);

	/**
	 * 加载用户列表
	 * 
	 * @param roleVo
	 * @return
	 */
	DataGridView queryAllUser(UserVo roleVo);

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 */
	User saveUser(User user);

	/**
	 * 更新 上传图片 更新一下下
	 *
	 * @param user
	 */
	User updateUser(User user);

	/**
	 * 查询用户最大排序码
	 * 
	 * @return
	 */
	Integer queryUserMaxOrderNum();

	/**
	 * 保存用户 与角色关系/
	 * 
	 * @param uid
	 * @param rids
	 */
	void saveUserRole(Integer uid, Integer[] rids);
}
