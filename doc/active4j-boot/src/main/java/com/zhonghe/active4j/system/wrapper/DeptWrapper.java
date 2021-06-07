package com.zhonghe.active4j.system.wrapper;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhonghe.active4j.common.Wrapper.BaseWrapper;
import com.zhonghe.active4j.system.entity.SysDepartEntity;
import com.zhonghe.active4j.system.util.SystemUtils;
public class DeptWrapper extends BaseWrapper<SysDepartEntity>{

	
	
	
	public DeptWrapper(List<SysDepartEntity> pageResult) {
		//父类中的方法初始化数据
		super(pageResult);
		
		
		//值替换
		this.getData().stream().forEach(d -> {
			if(StringUtils.isEmpty(d.getParentId())) {
				d.setParentId("-1");
			}
			
			//部门的处理
			d.setType(SystemUtils.getDictionaryValue("dept_type", d.getType()));
		});
		
	}

	
}
