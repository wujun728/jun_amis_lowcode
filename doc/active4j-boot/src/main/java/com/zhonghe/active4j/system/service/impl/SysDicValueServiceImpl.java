package com.zhonghe.active4j.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.system.dao.SysDicValueDao;
import com.zhonghe.active4j.system.entity.SysDicValueEntity;
import com.zhonghe.active4j.system.service.SysDicValueService;


/**
 *  数据字典service类
 * @author teli_
 *
 */
@Service("sysDicValueService")
@Transactional
public class SysDicValueServiceImpl extends ServiceImpl<SysDicValueDao, SysDicValueEntity> implements SysDicValueService {

	

}
