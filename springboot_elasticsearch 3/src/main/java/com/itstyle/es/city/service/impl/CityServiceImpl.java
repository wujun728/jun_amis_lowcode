package com.itstyle.es.city.service.impl;
import java.util.List;

import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.itstyle.es.city.entity.City;
import com.itstyle.es.city.repository.ElasticCityRepository;
import com.itstyle.es.city.service.CityService;
@Service("cityService")
public class CityServiceImpl implements CityService {
	private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
    
	@Autowired
	private ElasticCityRepository elasticCityRepository;
	 
	@Override
	public void saveCity(City city) {
		elasticCityRepository.save(city);
	}

	@Override
	public void getNearbyCities(double lat, double lon) {
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
        Page<City> cityPage = elasticCityRepository.search(searchQuery);
        logger.info("附近城市数量:{}",cityPage.getTotalElements());
        List<City> list =  cityPage.getContent();
        for(City city:list){
        	 logger.info("城市名称:{}",city.getName());
        }
	}

}
