package com.zhonghe.active4j.system.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.system.entity.SysUserEntity;
import com.zhonghe.active4j.system.util.SystemUtils;
public class UserWrapper extends BaseWrapper<SysUserEntity>{

	
	public UserWrapper(IPage<SysUserEntity> pageResult) {
		super(pageResult);
		
		pageResult.getRecords().forEach(d -> {
			d.setDeptId(SystemUtils.getDeptNameById(d.getDeptId()));
		});
	}
	
	
	
}
