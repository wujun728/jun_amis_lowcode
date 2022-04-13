package com.element.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.element.modules.sys.entity.SysUserTokenEntity;
import com.element.common.utils.ResultVo;

/**
 * 用户Token
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	ResultVo createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
