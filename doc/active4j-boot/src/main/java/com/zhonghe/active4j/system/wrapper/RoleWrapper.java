package com.zhonghe.active4j.system.wrapper;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.system.entity.SysRoleEntity;
public class RoleWrapper extends BaseWrapper<SysRoleEntity>{

	
	
	
	public RoleWrapper(List<SysRoleEntity> pageResult) {
		//父类中的方法初始化数据
		super(pageResult);
		
		
		//值替换
		this.getData().stream().forEach(d -> {
			if(StringUtils.isEmpty(d.getParentId())) {
				d.setParentId("-1");
			}
			
		});
		
	}

	
}
