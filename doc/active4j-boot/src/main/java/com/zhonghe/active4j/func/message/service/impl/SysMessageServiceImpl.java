package com.zhonghe.active4j.func.message.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhonghe.active4j.core.threadpool.ThreadPoolManager;
import com.zhonghe.active4j.core.util.DateUtils;
import com.zhonghe.active4j.func.message.dao.SysMessageDao;
import com.zhonghe.active4j.func.message.entity.SysMessageEntity;
import com.zhonghe.active4j.func.message.entity.SysUserMessageEntity;
import com.zhonghe.active4j.func.message.service.SysMessageService;
import com.zhonghe.active4j.func.message.service.SysUserMessageService;
import com.zhonghe.active4j.system.entity.SysUserEntity;
import com.zhonghe.active4j.system.service.SysUserService;
import com.zhonghe.active4j.system.util.SystemUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title SysMessageServiceImpl.java
 * @description 
		系统消息
 * @time  2019年12月18日 下午4:05:11
 * @author guyp
 * @version 1.0
 */
@Service("sysMessageService")
@Transactional
@Slf4j
public class SysMessageServiceImpl extends ServiceImpl<SysMessageDao, SysMessageEntity> implements SysMessageService {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserMessageService SysUserMessageService;
	
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
	public Model getMsgDetailModel(String id, Model model) {
		//根据id获取任务实体
		SysMessageEntity msg = this.getById(id);
		if(null != msg) {
			//系统消息类型的处理
			msg.setType(SystemUtils.getDictionaryValue("func_sys_message_type", msg.getType()));
			//系统消息状态的处理
			msg.setStatus(SystemUtils.getDictionaryValue("func_sys_message_status", msg.getStatus()));
			//发布时间的处理
			if(null != msg.getPublicTime()) {
				model.addAttribute("publicTime", DateUtils.getDate2Str(msg.getPublicTime()));
			}
			//赋值
			model.addAttribute("msg", msg);
		}
		return model;
	}

	/**
	 * 
	 * @description
	 *  	线程池发布系统消息
	 * @params
	 *      sysMessageEntity 系统消息实体
	 * @return void
	 * @author guyp
	 * @time 2019年12月19日 上午9:31:54
	 */
	public void publishSysMsg(SysMessageEntity sysMessageEntity) {
		log.info("进入给所有人发送消息逻辑");
		int totalNum = 0;
		//分页发给所有人
		//分页每页50
		int pageSize = 50;
		//获取用户分页数据
		IPage<SysUserEntity> pages = sysUserService.page(new Page<SysUserEntity>(1, pageSize));
		//取得总页数
		long totalPage = pages.getPages();
		log.info("给所有人发送消息,取得取得总页数：{}", totalPage);
		//分页遍历  每页开始发送
		for(long i = 1; i <= totalPage; i++) {
			log.info("给所有人发送消息，开始发送第{}页",i);
			IPage<SysUserEntity> lstResult = sysUserService.page(new Page<SysUserEntity>(i, pageSize));
			if(null != lstResult && null != lstResult.getRecords() && lstResult.getRecords().size() > 0) {
				for(SysUserEntity user : lstResult.getRecords()) {
					//启动线程池执行 群发
					totalNum++;
					//多线程异步执行
				    ThreadPoolManager.me().execute(() -> {
				    	//发送系统消息
				    	sendSysMsgToUser(sysMessageEntity, user);
				    });
				}
			}
		}
		log.info("群发系统消息，总共给{}客户发送了消息", totalNum);
		
		//发布时间赋值
		sysMessageEntity.setPublicTime(new Date());
		//发布状态设为已发布
		sysMessageEntity.setStatus(SysMessageEntity.Sys_Message_Status_Publish);
		//更新保存
		this.saveOrUpdate(sysMessageEntity);
		
	}
	
	/**
	 * 
	 * @description
	 *  	分页给所有用户发送系统消息
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月19日 上午10:21:14
	 */
	private void sendSysMsgToUser(SysMessageEntity sysMessageEntity, SysUserEntity sysUserEntity) {
		//用户消息实体赋值
		SysUserMessageEntity msg = new SysUserMessageEntity();
		msg.setCreateName("system");
		msg.setUserId(sysUserEntity.getId());
		msg.setTitle(sysMessageEntity.getTitle());
		msg.setContent(sysMessageEntity.getContent());
		msg.setType(sysMessageEntity.getType());
		msg.setPublicTime(new Date());
		//消息未读
		msg.setReadStatus(SysUserMessageEntity.Sys_User_Message_Read_No);
		//消息未删除
		msg.setDeleteStatus(SysUserMessageEntity.Sys_User_Message_Delete_No);
		msg.setSysMessageId(sysMessageEntity.getId());
		//保存
		SysUserMessageService.save(msg);
	}
	
}
