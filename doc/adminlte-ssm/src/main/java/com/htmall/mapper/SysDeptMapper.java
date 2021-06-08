package com.htmall.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.htmall.entity.SysDept;
import com.htmall.entity.excel.DeptExcel;

/**
 *
 * SysDept 表数据库控制层接口
 *
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

	List<DeptExcel> listExportDepe(Map<String, Object> queryParam);

}