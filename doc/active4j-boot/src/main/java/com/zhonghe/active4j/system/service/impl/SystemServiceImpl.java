package com.zhonghe.active4j.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhonghe.active4j.system.entity.SysDepartEntity;
import com.zhonghe.active4j.system.entity.SysDicEntity;
import com.zhonghe.active4j.system.entity.SysDicValueEntity;
import com.zhonghe.active4j.system.service.SysDepartService;
import com.zhonghe.active4j.system.service.SysDicService;
import com.zhonghe.active4j.system.service.SysDicValueService;
import com.zhonghe.active4j.system.service.SystemService;
import com.zhonghe.active4j.system.util.SystemUtils;

import lombok.extern.slf4j.Slf4j;

@Service("systemService")
@Transactional
@Slf4j
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SysDicService sysDicService;
	
	@Autowired
	private SysDicValueService sysDicValueService;
	
	@Autowired
	private SysDepartService sysDepartService;
	
	/**
	 * 初始化数据字典
	 */
	@Override
	public void initDictionary() {
		log.info("系统初始化数据字典....");
		List<SysDicEntity> lstDics = sysDicService.list();
		if(null != lstDics && lstDics.size() > 0) {
			for(SysDicEntity dic : lstDics) {
				QueryWrapper<SysDicValueEntity> queryWrapper = new QueryWrapper<SysDicValueEntity>();
				queryWrapper.eq("PARENT_ID", dic.getId());
				List<SysDicValueEntity> lstValues = sysDicValueService.list(queryWrapper);
				if(null != lstValues && lstValues.size() > 0) {
					Map<String, String> map = new HashMap<String, String>();
					for(SysDicValueEntity value : lstValues) {
						map.put(value.getValue(), value.getLabel());
					}
					SystemUtils.putDictionary(dic.getCode(), map);
					log.info("存入数据字典值:" + dic.getName());
				}
			}
		}
		log.info("系统初始化数据字典结束...");
	}

	/**
	 * 初始化部门信息
	 */
	public void initDeparts() {
		log.info("系统初始化部门数据......");
		
		List<SysDepartEntity> lstDeparts = sysDepartService.list();
		if(null != lstDeparts && lstDeparts.size() > 0) {
			for(SysDepartEntity depart : lstDeparts) {
				SystemUtils.putDept(depart.getId(), depart.getName());
			}
		}
		
		
		log.info("系统初始化部门数据结束......");
	}
	
}
