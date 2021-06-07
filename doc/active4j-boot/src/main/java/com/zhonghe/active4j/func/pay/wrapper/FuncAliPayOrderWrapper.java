package com.zhonghe.active4j.func.pay.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.func.pay.entity.FuncAliPayOrderEntity;

/**
 * @title FuncAliPayOrderWrapper.java
 * @description 
 * @time  2019年12月16日 下午2:11:45
 * @author mhm
 * @version 1.0
*/
public class FuncAliPayOrderWrapper extends BaseWrapper<FuncAliPayOrderEntity> {

	public FuncAliPayOrderWrapper(IPage<FuncAliPayOrderEntity> pageResult) {
		//父类中的方法初始化数据
		super(pageResult);
		
	}
}
