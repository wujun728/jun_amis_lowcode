package com.qingshop.mall.modules.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshop.mall.modules.mall.entity.MallAddress;
import com.qingshop.mall.modules.mall.mapper.MallAddressMapper;
import com.qingshop.mall.modules.mall.service.IMallAddressService;

/**
 * 收货地址表 服务实现类
 */
@Service
public class MallAddressServiceImpl extends ServiceImpl<MallAddressMapper, MallAddress> implements IMallAddressService {

}