package com.jun.plugin.ow.mapper;

import com.jun.plugin.common.base.BaseMapper;
import com.jun.plugin.ow.domain.Jobs;

import java.util.List;	

/**
 * 招贤纳士 数据层
 * 
 * @author admin
 * @date 2018-12-12
 */
public interface JobsMapper  extends BaseMapper<Jobs>
{
	/**
     * 查询招贤纳士信息
     * 
     * @param id 招贤纳士ID
     * @return 招贤纳士信息
     */
	public Jobs selectJobsById(Integer id);
	
	/**
     * 查询招贤纳士列表
     * 
     * @param jobs 招贤纳士信息
     * @return 招贤纳士集合
     */
	public List<Jobs> selectJobsList(Jobs jobs);

	/**
     * 查询招贤纳士列表 for官网
     *
     * @param jobs 招贤纳士信息
     * @return 招贤纳士集合
     */
	public List<Jobs> selectJobsListForOw(Jobs jobs);

	/**
     * 新增招贤纳士
     * 
     * @param jobs 招贤纳士信息
     * @return 结果
     */
	public int insertJobs(Jobs jobs);
	
	/**
     * 修改招贤纳士
     * 
     * @param jobs 招贤纳士信息
     * @return 结果
     */
	public int updateJobs(Jobs jobs);
	
	/**
     * 删除招贤纳士
     * 
     * @param id 招贤纳士ID
     * @return 结果
     */
	public int deleteJobsById(Integer id);
	
	/**
     * 批量删除招贤纳士
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJobsByIds(String[] ids);
	
}