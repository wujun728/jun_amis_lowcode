package com.qingshop.mall.modules.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.modules.mall.entity.MallOrderDetail;
import com.qingshop.mall.modules.mall.mapper.MallOrderDetailMapper;
import com.qingshop.mall.modules.mall.service.IMallOrderDetailService;

/**
 * 订单商品表 服务实现类
 */
@Service
public class MallOrderDetailServiceImpl extends ServiceImpl<MallOrderDetailMapper, MallOrderDetail> implements IMallOrderDetailService {

}