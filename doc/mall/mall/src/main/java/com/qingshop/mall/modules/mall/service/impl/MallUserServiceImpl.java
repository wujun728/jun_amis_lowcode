package com.qingshop.mall.modules.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.modules.mall.entity.MallUser;
import com.qingshop.mall.modules.mall.mapper.MallUserMapper;
import com.qingshop.mall.modules.mall.service.IMallUserService;

/**
 * 用户表 服务实现类
 */
@Service
public class MallUserServiceImpl extends ServiceImpl<MallUserMapper, MallUser> implements IMallUserService {

}
