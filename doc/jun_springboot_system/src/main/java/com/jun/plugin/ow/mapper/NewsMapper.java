package com.jun.plugin.ow.mapper;

import com.jun.plugin.common.base.BaseMapper;
import com.jun.plugin.ow.domain.News;

import java.util.List;

/**
 * 新闻 数据层
 * 
 * @author admin
 * @date 2018-12-10
 */
public interface NewsMapper extends BaseMapper<News>
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
     * 查询新闻列表
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
     * 删除新闻
     * 
     * @param newsId 新闻ID
     * @return 结果
     */
	public int deleteNewsById(Integer newsId);
	
	/**
     * 批量删除新闻
     * 
     * @param newsIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteNewsByIds(String[] newsIds);
	
}