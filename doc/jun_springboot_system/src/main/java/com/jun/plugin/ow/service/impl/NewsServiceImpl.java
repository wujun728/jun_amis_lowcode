package com.jun.plugin.ow.service.impl;

import com.jun.plugin.common.support.Convert;
import com.jun.plugin.ow.domain.News;
import com.jun.plugin.ow.mapper.NewsMapper;
import com.jun.plugin.ow.service.INewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻 服务层实现
 * 
 * @author admin
 * @date 2018-12-10
 */
@Service
public class NewsServiceImpl implements INewsService
{
	@Autowired
	private NewsMapper newsMapper;

	/**
     * 查询新闻信息
     * 
     * @param newsId 新闻ID
     * @return 新闻信息
     */
    @Override
	public News selectNewsById(Integer newsId)
	{
	    return newsMapper.selectNewsById(newsId);
	}
	
	/**
     * 查询新闻列表
     * 
     * @param news 新闻信息
     * @return 新闻集合
     */
	@Override
	public List<News> selectNewsList(News news)
	{
	    return newsMapper.selectNewsList(news);
	}

	/**
	 * 查询新闻列表(官网)
	 *
	 * @param news 新闻信息
	 * @return 新闻集合
	 */
	@Override
	public List<News> selectNewsListForOw(News news)
	{
		news.setStatus("0");
		return newsMapper.selectNewsList(news);
	}
    /**
     * 新增新闻
     * 
     * @param news 新闻信息
     * @return 结果
     */
	@Override
	public int insertNews(News news)
	{
	    return newsMapper.insertNews(news);
	}
	
	/**
     * 修改新闻
     * 
     * @param news 新闻信息
     * @return 结果
     */
	@Override
	public int updateNews(News news)
	{


	    return newsMapper.updateNews(news);
	}

	/**
     * 删除新闻对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteNewsByIds(String ids)
	{
		return newsMapper.deleteNewsByIds(Convert.toStrArray(ids));
	}

	@Override
	public News getNewsByIds(Long id) {
		return newsMapper.selectByPrimaryKey(id);
	}

}
