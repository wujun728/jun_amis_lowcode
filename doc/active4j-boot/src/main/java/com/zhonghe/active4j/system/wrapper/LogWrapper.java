package com.zhonghe.active4j.system.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.system.entity.SysLogEntity;
import com.zhonghe.active4j.system.util.SystemUtils;
public class LogWrapper extends BaseWrapper<SysLogEntity>{

	public LogWrapper(IPage<SysLogEntity> pageResult) {
		//父类中的方法初始化数据
		super(pageResult);
		
		//值替换
		this.getData().stream().forEach(d -> {
			//日志类型的处理
			d.setType(SystemUtils.getDictionaryValue("log_type", d.getType()));
		});
		
	}
	
	
	
}
