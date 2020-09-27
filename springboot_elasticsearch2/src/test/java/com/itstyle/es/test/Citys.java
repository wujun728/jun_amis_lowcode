package com.itstyle.es.test;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itstyle.es.Application;
import com.itstyle.es.city.entity.City;
import com.itstyle.es.city.service.CityService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class Citys {
	@Autowired
	private  CityService cityService;
	@Autowired
	private  ElasticsearchTemplate elasticSearchTemplate;
	@Autowired
	private  ElasticsearchOperations  eslasticsearchOperations;
	
	//@Test
	public void  save(){
		try {
			System.out.println("组织数据开始");
			cityService.saveCity(new City(1L, "北京","39.92,116.46", "中国人民站起来了，北京人民可以天天站在天安门广场吃烤鸭了"));
			cityService.saveCity(new City(2L, "天津", "39.14,117.21", "中国人民站起来了，天津人民可以天天在迎宾广场吃麻花了"));  
			cityService.saveCity(new City(3L, "青岛", "36.11,120.38", "中国人民站起来了，青岛人民可以天天在！最后一次，不要错过今天"));  
			cityService.saveCity(new City(4L, "哈尔滨", "45.77,126.66", "中国人民站起来了，哈尔滨人民可以天天站在索菲亚广场吃红肠了"));  
			cityService.saveCity(new City(5L, "乌鲁木齐", "43.84,87.56", "中国人民站起来了，乌鲁木齐人民可以天天在人民广场啃羊腿了"));  
			cityService.saveCity(new City(6L, "三亚", "18.25,109.52", "中国人民站起来了，三亚人民可以让青岛政府去丢吧，让他们创城去吧！"));  
			System.out.println("组织数据结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void  search(){
		cityService.getNearbyCities(39.92,116.46);
	}
	//@Test
	public void  create(){
		XContentBuilder mapping = null;
		try {
			mapping = jsonBuilder()
			.startObject()
			.startObject("_ttl")
				.field("enabled",false)
				.endObject()
					.startObject("properties")
					.startObject("id")
							.field("type","long")
					.endObject()
					.startObject("name")
						.field("type","string")
					.endObject()
					.startObject("location")
						.field("type","geo_point")
					.endObject()
					.startObject("introduction")
						.field("type","string")
					.endObject()
				.endObject()
			.endObject();
			PutMappingRequest request = Requests.putMappingRequest("elasticsearch").type("city").source(mapping);
			eslasticsearchOperations.getClient().admin().indices().putMapping(request).actionGet();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
