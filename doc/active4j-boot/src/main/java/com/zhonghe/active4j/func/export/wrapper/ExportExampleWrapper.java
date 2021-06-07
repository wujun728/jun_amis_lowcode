package com.zhonghe.active4j.func.export.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.func.export.entity.ExportExampleEntity;

/**
 * 
 * @title ExportExampleWrapper.java
 * @description 
 * @time  2019年12月17日 上午10:50:34
 * @author guyp
 * @version 1.0
 */
public class ExportExampleWrapper extends BaseWrapper<ExportExampleEntity>{

	public ExportExampleWrapper(IPage<ExportExampleEntity> pageResult) {
		//父类中的方法初始化数据
		super(pageResult);
		
	}
	
}
