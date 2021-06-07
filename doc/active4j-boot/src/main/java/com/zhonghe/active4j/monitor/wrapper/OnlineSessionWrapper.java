package com.zhonghe.active4j.monitor.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.monitor.entity.OnlineSessionEntity;



public class OnlineSessionWrapper extends BaseWrapper<OnlineSessionEntity>{

	
	public OnlineSessionWrapper(IPage<OnlineSessionEntity> pageResult) {
		super(pageResult);
		
		pageResult.getRecords().forEach(d -> {
			
		});
	}
	
	
	
}
