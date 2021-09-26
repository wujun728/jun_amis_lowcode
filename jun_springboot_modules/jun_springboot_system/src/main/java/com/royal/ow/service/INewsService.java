package com.royal.ow.service;

import com.royal.ow.domain.News;

import java.util.List;

/**
 * 新闻 服务层
 * 
 * @author royal
 * @date 2018-12-10
 */
public interface INewsService 
{
	/**
     * 查询新闻信息
     * 
     * @param newsId 新闻ID
     * @return 新闻信息
     */
	public News selectNewsById(Integer newsId);
	
	/**
     * 查询新闻列表
     * 
     * @param news 新闻信息
     * @return 新闻集合
     */
	public List<News> selectNewsList(News news);
	/**
	 * 查询新闻列表(官网)
	 *
	 * @param news 新闻信息
	 * @return 新闻集合
	 */
	public List<News> selectNewsListForOw(News news);

	/**
     * 新增新闻
     * 
     * @param news 新闻信息
     * @return 结果
     */
	public int insertNews(News news);
	
	/**
     * 修改新闻
     * 
     * @param news 新闻信息
     * @return 结果
     */
	public int updateNews(News news);
		
	/**
     * 删除新闻信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNewsByIds(String ids);


	/**
	 * 新闻详情
	 *
	 * @param id 新闻详情
	 * @return 结果
	 */
	public News getNewsByIds(Long id);
	
}
