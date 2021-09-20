package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.BizCustomerMapper;
import com.company.project.entity.BizCustomerEntity;
import com.company.project.service.BizCustomerService;


@Service("bizCustomerService")
public class BizCustomerServiceImpl extends ServiceImpl<BizCustomerMapper, BizCustomerEntity> implements BizCustomerService {


}