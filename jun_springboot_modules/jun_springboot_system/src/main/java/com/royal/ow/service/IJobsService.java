package com.royal.ow.service;

import com.royal.ow.domain.Jobs;
import java.util.List;

/**
 * 招贤纳士 服务层
 * 
 * @author royal
 * @date 2018-12-12
 */
public interface IJobsService 
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
     * 查询招贤纳士列表
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
     * 删除招贤纳士信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteJobsByIds(String ids);

	/**
	 * 详情
	 * @param id
	 * @return
	 */
	public Jobs getNewsByIds(Long id);

}
