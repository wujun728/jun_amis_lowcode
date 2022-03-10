package com.jun.plugin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.system.domain.Menu;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/18
 *
 * 
 * 
 * @since JDK 1.8
 **/

public interface MenuMapper extends BaseMapper<Menu> {
	Integer queryMenuMaxOrderNum();

	Integer queryMenuChildrenCountById(Integer id);
}