package com.qingshop.mall.modules.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.modules.mall.entity.MallCart;
import com.qingshop.mall.modules.mall.mapper.MallCartMapper;
import com.qingshop.mall.modules.mall.service.IMallCartService;

/**
 * 购物车商品表 服务实现类
 */
@Service
public class MallCartServiceImpl extends ServiceImpl<MallCartMapper, MallCart> implements IMallCartService {

}