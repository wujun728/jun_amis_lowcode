package com.itstyle.es.area.service.impl;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
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

import com.itstyle.es.area.entity.Area;
import com.itstyle.es.area.repository.ElasticAreaRepository;
import com.itstyle.es.area.service.AreaService;
import com.itstyle.es.common.constant.PageConstant;
import com.itstyle.es.log.entity.Pages;
@Service("areaService")
public class AreaServiceImpl implements AreaService {
	private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
    
	@Autowired
	private ElasticAreaRepository elasticAreaRepository;
	 
	@Override
	public void saveArea(Area area) {
		elasticAreaRepository.save(area);
	}
    
	@Override
	public Pages<Area> searchAreaPage(Integer pageNumber, Integer pageSize,
			String searchContent) {
		// 校验分页参数
        if (pageSize == null || pageSize <= 0) {
            pageSize = PageConstant.PAGE_SIZE;
        }

        if (pageNumber == null || pageNumber < PageConstant.DEFAULT_PAGE_NUMBER) {
            pageNumber = PageConstant.DEFAULT_PAGE_NUMBER;
        }

        // 构建搜索查询
        SearchQuery searchQuery = getLogSearchQuery(pageNumber,pageSize,searchContent);

        logger.info("searchLogPage: searchContent [{}] \n DSL  = \n {}",searchContent,searchQuery.getQuery().toString());

        Page<Area> areaPage = elasticAreaRepository.search(searchQuery);
        Pages<Area> pages = new Pages<Area>();
        pages.setRows(areaPage.getContent());
        pages.setTotal((int)areaPage.getTotalElements());
        pages.setTotalPages(areaPage.getTotalPages());
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
    private SearchQuery  getLogSearchQuery(Integer pageNumber, Integer pageSize,String searchContent) {
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
        //设置多字段组合模糊搜索
        if(StringUtils.isNotBlank(searchContent)){
        	builder.must(QueryBuilders.multiMatchQuery(searchContent,"name"));
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
    
    @Override
	public void getNearbyAreas(double lat, double lon) {
		/**
		 * 通过地理坐标点过滤有四种地理坐标点相关的过滤方式可以用来选中或者排除文档：
		 * geo_bounding_box:: 
		 * 找出落在指定矩形框中的坐标点
		 * geo_distance:: 
		 * 找出与指定位置在给定距离内的点
		 * geo_distance_range:: 
		 * 找出与指定点距离在给定最小距离和最大距离之间的点
		 * geo_polygon:: 
		 * 找出落在多边形中的点。这个过滤器使用代价很大。当你觉得自己需要使用它，最好先看看 geo-shapes
		 */
		//创建builder
		QueryBuilder  builder = QueryBuilders.geoDistanceRangeQuery("location")
        		.point(lat,lon)//纬度在前，经度在后  
                .from("0km")  
                .to("400km")  
                .includeLower(true)  
                .includeUpper(false)  
                .optimizeBbox("memory")  
                .geoDistance(GeoDistance.ARC);  
        //字段精确匹配
	    GeoDistanceSortBuilder sort = new GeoDistanceSortBuilder("location");  
        sort.unit(DistanceUnit.KILOMETERS);//距离单位公里  
        sort.order(SortOrder.ASC);  
        sort.point(lat,lon);//注意纬度在前，经度在后  
        SearchQuery searchQuery   = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withSort(sort)
                .build();
        logger.info("getNearbyCities:  DSL  = \n {}",searchQuery.getQuery().toString());
        Page<Area> areaPage = elasticAreaRepository.search(searchQuery);
        logger.info("附近地区数量:{}",areaPage.getTotalElements());
        List<Area> list =  areaPage.getContent();
        for(Area area:list){
        	 logger.info("地区名称:{}",area.getName());
        }
	}

}