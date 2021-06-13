package com.qingshop.mall.modules.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.modules.mall.entity.MallOrder;
import com.qingshop.mall.modules.mall.mapper.MallOrderMapper;
import com.qingshop.mall.modules.mall.service.IMallOrderService;

/**
 * 订单表 服务实现类
 */
@Service
public class MallOrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements IMallOrderService {

}