package com.element.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 */
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptchaEntity> {

}
