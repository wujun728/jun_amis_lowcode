package com.jun.plugin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.plugin.system.domain.Dept;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/17
 * 
 * 
 * @since JDK 1.8
 */
public interface DeptMapper extends BaseMapper<Dept> {
	Integer queryDeptMaxOrderNum();

	Integer queryDeptChildrenCountById(Integer id);
}