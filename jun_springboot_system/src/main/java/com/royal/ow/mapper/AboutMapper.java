package com.royal.ow.mapper;

import com.royal.common.base.BaseMapper;
import com.royal.ow.domain.About;

import java.util.List;	

/**
 * 关于我们 数据层
 * 
 * @author royal
 * @date 2018-12-13
 */
public interface AboutMapper extends BaseMapper<About>
{
	/**
     * 查询关于我们信息
     * 
     * @param id 关于我们ID
     * @return 关于我们信息
     */
	public About selectAboutById(Integer id);
	
	/**
     * 查询关于我们列表
     * 
     * @param about 关于我们信息
     * @return 关于我们集合
     */
	public List<About> selectAboutList(About about);
	
	/**
     * 新增关于我们
     * 
     * @param about 关于我们信息
     * @return 结果
     */
	public int insertAbout(About about);
	
	/**
     * 修改关于我们
     * 
     * @param about 关于我们信息
     * @return 结果
     */
	public int updateAbout(About about);
	
	/**
     * 删除关于我们
     * 
     * @param id 关于我们ID
     * @return 结果
     */
	public int deleteAboutById(Integer id);
	
	/**
     * 批量删除关于我们
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAboutByIds(String[] ids);
	
}