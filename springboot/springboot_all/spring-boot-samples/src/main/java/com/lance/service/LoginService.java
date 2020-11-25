package com.lance.service;

import com.lance.entity.UserEntity;

public interface LoginService {

	/**
	 * 用户登录
	 * @author Wujun
	 * 2014-6-11下午11:26:05
	 * @param user
	 * @return
	 */
	UserEntity login(UserEntity user);
}
