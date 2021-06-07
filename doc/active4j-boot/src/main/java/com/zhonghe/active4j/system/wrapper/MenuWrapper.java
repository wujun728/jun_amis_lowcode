package com.zhonghe.active4j.system.wrapper;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.system.entity.SysMenuEntity;
import com.zhonghe.active4j.system.util.SystemUtils;

public class MenuWrapper extends BaseWrapper<SysMenuEntity>{

	
	public MenuWrapper(List<SysMenuEntity> pageResult) {
		//父类中的方法初始化数据
		super(pageResult);
		
		
		//值替换
		this.getData().stream().forEach(d -> {
			if(StringUtils.isEmpty(d.getParentId())) {
				d.setParentId("-1");
			}
			
			//菜单类型的处理
			d.setType(SystemUtils.getDictionaryValue("menu_type", d.getType()));
		});
		
	}
}
