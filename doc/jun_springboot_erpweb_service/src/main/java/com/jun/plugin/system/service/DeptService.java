package com.jun.plugin.system.service;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.system.common.DataGridView;
import com.jun.plugin.system.domain.Dept;
import com.jun.plugin.system.vo.DeptVo;

/**
 * ClassName: Loginfo Description: layui date: 2020/4/17
 *
 * 
 * 
 * @since JDK 1.8
 */
public interface DeptService extends IService<Dept> {

	/**
	 * 查询部门数据
	 *
	 * @param deptVo
	 * @return
	 */
	DataGridView queryAllDept(DeptVo deptVo);

	/**
	 * 保存部门
	 *
	 * @param dept
	 * @return 对象 用来做缓存
	 */
	Dept saveDept(Dept dept);

	/**
	 * 查询部门最大的排序码
	 *
	 * @return
	 */
	Integer queryDeptMaxOrderNum();

	/**
	 * 修改部门
	 *
	 * @param dept
	 * @return
	 */
	Dept updateDept(@Param("dept") Dept dept);

	/**
	 * 根据id查询当前部门 子部门的 个数
	 *
	 * @param id
	 * @return
	 */
	Integer getDeptChildrenCountById(Integer id);

	/**
	 * 清理缓存
	 */
	void clearAllRedisDB();
}
