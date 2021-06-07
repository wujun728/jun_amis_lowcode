package com.zhonghe.active4j.func.message.service;

import org.springframework.ui.Model;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhonghe.active4j.func.message.entity.SysMessageEntity;

/**
 * 
 * @title SysMessageService.java
 * @description 
		系统消息
 * @time  2019年12月18日 下午4:02:07
 * @author guyp
 * @version 1.0
 */
public interface SysMessageService extends IService<SysMessageEntity> {
	
	/**
	 * 
	 * @description
	 *  	获取系统消息明细
	 * @params
	 *      id 系统消息id
	 * @return Model
	 * @author guyp
	 * @time 2019年12月18日 下午5:01:13
	 */
	public Model getMsgDetailModel(String id, Model model);
	
	/**
	 * 
	 * @description
	 *  	发布系统消息
	 * @params
	 *      sysMessageEntity 系统消息实体
	 * @return void
	 * @author guyp
	 * @time 2019年12月19日 上午9:31:54
	 */
	public void publishSysMsg(SysMessageEntity sysMessageEntity);
}
