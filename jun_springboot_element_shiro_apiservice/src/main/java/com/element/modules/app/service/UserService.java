package com.element.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.element.modules.app.entity.UserEntity;
import com.element.modules.app.form.LoginForm;

/**
 * 用户
 */
public interface UserService extends IService<UserEntity> {

	UserEntity getUserByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	long login(LoginForm form);
}
