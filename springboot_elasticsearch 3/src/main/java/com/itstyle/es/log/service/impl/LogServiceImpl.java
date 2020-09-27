package com.itstyle.es.log.service.impl;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.itstyle.es.common.constant.PageConstant;
import com.itstyle.es.log.entity.Pages;
import com.itstyle.es.log.entity.SysLogs;
import com.itstyle.es.log.repository.ElasticLogRepository;
import com.itstyle.es.log.service.LogService;
@Service("logService")
public class LogServiceImpl implements LogService {
	private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    
	@Autowired
	private ElasticLogRepository elasticLogRepository;
	 
	@Override
	public void saveLog(SysLogs log) {
		elasticLogRepository.save(log);
	}
    
	@Override
	public Pages<SysLogs> searchLogPage(Integer pageNumber, Integer pageSize, Integer platFrom,
			String searchContent) {
		// 校验分页参数
        if (pageSize == null || pageSize <= 0) {
            pageSize = PageConstant.PAGE_SIZE;
        }

        if (pageNumber == null || pageNumber < PageConstant.DEFAULT_PAGE_NUMBER) {
            pageNumber = PageConstant.DEFAULT_PAGE_NUMBER;
        }

        // 构建搜索查询
        SearchQuery searchQuery = getLogSearchQuery(pageNumber,pageSize, platFrom,searchContent);

        logger.info("searchLogPage: searchContent [{}] \n DSL  = \n {}",searchContent,searchQuery.getQuery().toString());

        Page<SysLogs> logPage = elasticLogRepository.search(searchQuery);
        Pages<SysLogs> pages = new Pages<SysLogs>();
        pages.setRows(logPage.getContent());
        pages.setTotal((int)logPage.getTotalElements());
        pages.setTotalPages(logPage.getTotalPages());
        return pages;
	}
	
	 /**
     * 根据搜索词构造搜索查询语句
     * 代码流程：
     *      - 精确查询
     *      - 模糊查询
     *      - 排序查询
     *      - 设置分页参数
     * @param pageNumber 当前页码
     * @param pageSize 每页大小
     * @param searchContent 搜索内容
     * @return
     */
    private SearchQuery  getLogSearchQuery(Integer pageNumber, Integer pageSize,Integer platFrom,String searchContent) {
    	//创建builder
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        /**
         *  must
			所有的语句都 必须（must） 匹配，与 AND 等价。
			must_not
			所有的语句都 不能（must not） 匹配，与 NOT 等价。
			should
			至少有一个语句要匹配，与 OR 等价。
			trem
			精确查找 与= 号等价。
			match
			模糊匹配 与like 等价。
         */
        //字段精确匹配
        if(platFrom!=null){
        	builder.must(QueryBuilders.termQuery("platFrom", platFrom));
        }
        //设置多字段组合模糊搜索
        if(StringUtils.isNotBlank(searchContent)){
        	builder.must(QueryBuilders.multiMatchQuery(searchContent,"username","operation","exceptionDetail"));
        }
        //设置排序
        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        //设置分页
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(builder)
                .withSort(sort)
                .build();
    }

}
