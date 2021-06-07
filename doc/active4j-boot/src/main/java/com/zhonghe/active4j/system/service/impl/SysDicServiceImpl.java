package com.zhonghe.active4j.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.system.dao.SysDicDao;
import com.zhonghe.active4j.system.entity.SysDicEntity;
import com.zhonghe.active4j.system.service.SysDicService;


/**
 * 数据字典管理service类
 * @author teli_
 *
 */
@Service("sysDicService")
@Transactional
public class SysDicServiceImpl extends ServiceImpl<SysDicDao, SysDicEntity> implements SysDicService {

	

}
