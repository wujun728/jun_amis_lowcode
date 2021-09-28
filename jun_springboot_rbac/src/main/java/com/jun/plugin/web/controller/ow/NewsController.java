package com.jun.plugin.web.controller.ow;

import com.jun.plugin.common.annotation.Log;
import com.jun.plugin.common.base.AjaxResult;
import com.jun.plugin.common.enums.BusinessType;
import com.jun.plugin.common.utils.ExcelUtil;
import com.jun.plugin.framework.util.ShiroUtils;
import com.jun.plugin.framework.web.base.BaseController;
import com.jun.plugin.framework.web.page.TableDataInfo;
import com.jun.plugin.ow.domain.News;
import com.jun.plugin.ow.service.INewsService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 新闻 信息操作处理
 * 
 * @author admin
 * @date 2018-12-10
 */
@Controller
@RequestMapping("/ow/news")
public class NewsController extends BaseController
{
    private String prefix = "ow/news";
	
	@Autowired
	private INewsService newsService;
	
	@RequiresPermissions("ow:news:view")
	@GetMapping()
	public String news()
	{
	    return prefix + "/news";
	}
	
	/**
	 * 查询新闻列表
	 */
	@RequiresPermissions("ow:news:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(News news)
	{
		startPage();
        List<News> list = newsService.selectNewsList(news);
		return getDataTable(list);
	}


	/**
	 * 导出新闻列表
	 */
	@RequiresPermissions("ow:news:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(News news)
    {
    	List<News> list = newsService.selectNewsList(news);
        ExcelUtil<News> util = new ExcelUtil<News>(News.class);
        return util.exportExcel(list, "news");
    }
	
	/**
	 * 新增新闻
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存新闻
	 */
	@RequiresPermissions("ow:news:add")
	@Log(title = "新闻", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(News news)
	{
		news.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(newsService.insertNews(news));
	}

	/**
	 * 修改新闻
	 */
	@GetMapping("/edit/{newsId}")
	public String edit(@PathVariable("newsId") Integer newsId, ModelMap mmap)
	{
		News news = newsService.selectNewsById(newsId);
		mmap.put("news", news);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存新闻
	 */
	@RequiresPermissions("ow:news:edit")
	@Log(title = "新闻", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(News news)
	{
		news.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(newsService.updateNews(news));
	}
	
	/**
	 * 删除新闻
	 */
	@RequiresPermissions("ow:news:remove")
	@Log(title = "新闻", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(newsService.deleteNewsByIds(ids));
	}
	
}
