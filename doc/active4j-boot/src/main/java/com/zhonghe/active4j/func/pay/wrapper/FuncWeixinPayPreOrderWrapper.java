package com.zhonghe.active4j.func.pay.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.func.pay.entity.FuncWeixinPayPreOrderEntity;

/**
 * @title FuncWeixinPayPreOrderWrapper.java
 * @description 
 * @time  2019年12月12日 下午4:51:48
 * @author mhm
 * @version 1.0
*/
public class FuncWeixinPayPreOrderWrapper extends BaseWrapper<FuncWeixinPayPreOrderEntity> {

	public FuncWeixinPayPreOrderWrapper(IPage<FuncWeixinPayPreOrderEntity> pageResult) {
		//父类中的方法初始化数据
		super(pageResult);
		
	}
}
