package com.element.modules.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
