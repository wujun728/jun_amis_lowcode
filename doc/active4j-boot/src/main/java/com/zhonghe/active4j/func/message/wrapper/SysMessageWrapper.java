package com.zhonghe.active4j.func.message.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.func.message.entity.SysMessageEntity;
import com.zhonghe.active4j.system.util.SystemUtils;

/**
 * 
 * @title SysMessageWrapper.java
 * @description 
 * @time  2019年12月18日 下午4:10:28
 * @author guyp
 * @version 1.0
 */
public class SysMessageWrapper extends BaseWrapper<SysMessageEntity>{

	public SysMessageWrapper(IPage<SysMessageEntity> pageResult) {
		//父类中的方法初始化数据
		super(pageResult);
		
		//值替换
		this.getData().stream().forEach(d -> {
			//消息类型的处理
			d.setType(SystemUtils.getDictionaryValue("func_sys_message_type", d.getType()));
		});
	}
	
}
