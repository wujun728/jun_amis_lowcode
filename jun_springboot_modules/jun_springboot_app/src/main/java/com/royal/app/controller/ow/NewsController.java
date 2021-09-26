package com.royal.app.controller.ow;

import com.royal.app.common.base.BaseController;
import com.royal.app.common.page.ResultData;
import com.royal.ow.domain.News;
import com.royal.ow.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新闻 信息操作处理
 * 
 * @author royal
 * @date 2018-12-10
 */
@Controller
@RequestMapping("/ow/news")
public class NewsController extends BaseController
{

	@Autowired
	private INewsService newsService;
	

	/**
	 * 查询新闻列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public ResultData list(@RequestBody News news)
	{
		startPage();
        List<News> list = newsService.selectNewsListForOw(news);
		return getDataPage(list);
	}


	/**
	 * 查询新闻列表
	 */
	@PostMapping("/list2")
	@ResponseBody
	public List<News> list2( News news,HttpServletRequest request)
	{
		System.out.println(getHeadersInfo(request));
		List<News> list = newsService.selectNewsListForOw(news);
		return list;
	}

	/**
	 * 查询新闻列表
	 */
	@PostMapping("/list3")
	@ResponseBody
	public News list3( News news,HttpServletRequest request)
	{
		System.out.println(getHeadersInfo(request));
		return news;
	}

	/**
	 * 描述:获取请求头内容
	 */
	private String getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		String result="";
		for (String key : map.keySet()) {
			//System.out.println("key= "+ key + " and value= " + map.get(key));
			result = result + key + ":" + map.get(key)+"\n";
		}
		return result;
	}


	/**
	 * 详情
	 */
	@PostMapping("/getById")
	@ResponseBody
	public News editSave(@RequestBody News news)
	{
		return  newsService.getNewsByIds(news.getNewsId());
	}

	
}
