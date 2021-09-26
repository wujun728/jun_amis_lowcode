package com.royal.ow.service.impl;

import com.royal.common.support.Convert;
import com.royal.ow.domain.Jobs;
import com.royal.ow.mapper.JobsMapper;
import com.royal.ow.service.IJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招贤纳士 服务层实现
 * 
 * @author royal
 * @date 2018-12-12
 */
@Service
public class JobsServiceImpl implements IJobsService 
{
	@Autowired
	private JobsMapper jobsMapper;

	/**
     * 查询招贤纳士信息
     * 
     * @param id 招贤纳士ID
     * @return 招贤纳士信息
     */
    @Override
	public Jobs selectJobsById(Integer id)
	{
	    return jobsMapper.selectJobsById(id);
	}
	
	/**
     * 查询招贤纳士列表
     * 
     * @param jobs 招贤纳士信息
     * @return 招贤纳士集合
     */
	@Override
	public List<Jobs> selectJobsList(Jobs jobs)
	{
	    return jobsMapper.selectJobsList(jobs);
	}

	@Override
	public List<Jobs> selectJobsListForOw(Jobs jobs) {
		jobs.setStatus("0");
		return jobsMapper.selectJobsListForOw(jobs);
	}

	/**
     * 新增招贤纳士
     * 
     * @param jobs 招贤纳士信息
     * @return 结果
     */
	@Override
	public int insertJobs(Jobs jobs)
	{
	    return jobsMapper.insertJobs(jobs);
	}
	
	/**
     * 修改招贤纳士
     * 
     * @param jobs 招贤纳士信息
     * @return 结果
     */
	@Override
	public int updateJobs(Jobs jobs)
	{
	    return jobsMapper.updateJobs(jobs);
	}

	/**
     * 删除招贤纳士对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJobsByIds(String ids)
	{
		return jobsMapper.deleteJobsByIds(Convert.toStrArray(ids));
	}

	@Override
	public Jobs getNewsByIds(Long id) {
		return jobsMapper.selectByPrimaryKey(id);
	}

}
