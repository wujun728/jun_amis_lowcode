package com.royal.ow.service;

import com.royal.ow.domain.About;
import java.util.List;

/**
 * 关于我们 服务层
 * 
 * @author royal
 * @date 2018-12-13
 */
public interface IAboutService 
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
     * 查询关于我们列表for官网
     *
     * @param about 关于我们信息
     * @return 关于我们集合
     */
    public List<About> selectAboutListForOw(About about);
	
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
     * 删除关于我们信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAboutByIds(String ids);

    /**
     * 通过ID查看详情
     * @param id
     * @return
     */
    public About getAboutByIds(Long id);
	
}
