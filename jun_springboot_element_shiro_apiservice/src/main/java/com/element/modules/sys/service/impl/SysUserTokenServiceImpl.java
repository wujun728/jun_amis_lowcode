package com.element.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.element.common.utils.ResultVo;
import com.element.modules.sys.mapper.SysUserTokenMapper;
import com.element.modules.sys.entity.SysUserTokenEntity;
import com.element.modules.sys.oauth2.TokenGenerator;
import com.element.modules.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserTokenEntity> implements SysUserTokenService {

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public ResultVo createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysUserTokenEntity tokenEntity = this.getById(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            this.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            this.updateById(tokenEntity);
        }
        ResultVo resultVo = ResultVo.ok().put("token", token).put("expire", EXPIRE);
        return resultVo;
    }

    @Override
    public void logout(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();
        //修改token
        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}
