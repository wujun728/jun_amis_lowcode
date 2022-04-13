package com.element.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.element.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 */
@Mapper
public interface SysUserTokenMapper extends BaseMapper<SysUserTokenEntity> {

    /**
     * 获取token信息
     * @param token
     * @return
     */
    SysUserTokenEntity getTokenInfo(String token);
	
}
