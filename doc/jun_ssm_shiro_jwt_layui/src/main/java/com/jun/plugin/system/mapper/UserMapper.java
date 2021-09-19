package com.jun.plugin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.system.domain.User;

/**
 * ClassName: UserMapper Description: layui date: 2020/4/14 19:50
 * 
 * 
 * @version
 * @since JDK 1.8
 */
public interface UserMapper extends BaseMapper<User> {
	Integer queryUserMaxOrderNum();
}