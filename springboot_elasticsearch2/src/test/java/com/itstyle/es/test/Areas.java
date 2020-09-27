package com.itstyle.es.test;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itstyle.es.Application;
import com.itstyle.es.area.entity.Area;
import com.itstyle.es.area.service.AreaService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class Areas {
	@Autowired
	private  AreaService areaService;
	@Autowired
	private  ElasticsearchTemplate elasticSearchTemplate;
	@Autowired
	private  ElasticsearchOperations  eslasticsearchOperations;
	
	//@Test
	public void  save(){
		try {
			System.out.println("组织数据开始");
			 
			System.out.println("组织数据结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void  search(){
		areaService.getNearbyAreas(31.91833,94.05316);
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
					.startObject("pid")//父id
						.field("type","long")
					.endObject()
					.startObject("shortname")//简称
						.field("type","string")
					.endObject()
					.startObject("name")//名称
						.field("type","string")
					.endObject()
					.startObject("mergerName")//全称 
						.field("type","string")
					.endObject()
					.startObject("level")//层级 0 1 2 省市区县
						.field("type","short")
					.endObject()
					.startObject("pinyin")//拼音
						.field("type","string")
					.endObject()
					.startObject("code") //长途区号
						.field("type","string")
					.endObject()
					.startObject("zipCode")//邮编
						.field("type","string")
					.endObject()
					.startObject(" first")//首字母
						.field("type","string")
					.endObject()
					.startObject("location")//经纬度
						.field("type","geo_point")
					.endObject()
				.endObject()
			.endObject();
			PutMappingRequest request = Requests.putMappingRequest("elasticsearch").type("area").source(mapping);
			eslasticsearchOperations.getClient().admin().indices().putMapping(request).actionGet();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/seckill?characterEncoding=UTF-8";
	private static final String USERNAME = "root";
	private static final String PASS = "123456";
	
	@Test
	public void  saveAreaBySql(){
		List<Area> areaList = readSceneList();
		System.out.println(areaList.size());
		bulkIndex(areaList);
	}
	/**
	 * 返回List
	 * @Author  小柒2012
	 * @return  List<Area>
	 */
	public List<Area> readSceneList(){
		List<Area> areaList = new ArrayList<Area>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = JDBCConnect.getConnect(URL,USERNAME,PASS);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM sh_area";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Area area = new Area();
				area.setId(rs.getLong(1));
				area.setPid(rs.getLong(2));
				area.setShortname(rs.getString(3));
				area.setName(rs.getString(4));
				area.setMergerName(rs.getString(5));
				area.setLevel(rs.getShort(6));
				area.setPinyin(rs.getString(7));
				area.setCode(rs.getString(8));
				area.setZipCode(rs.getString(9));
				area.setFirst(rs.getString(10));
				area.setLocation(rs.getString(12)+","+rs.getString(11));
				areaList.add(area);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return areaList;
	}
	//批量同步或者插入数据
	public void bulkIndex(List<Area> areaList) {  
		long start = System.currentTimeMillis();
        int counter = 0;  
        try {  
            List<IndexQuery> queries = new ArrayList<>();  
            for (Area area : areaList) {  
                IndexQuery indexQuery = new IndexQuery();  
                indexQuery.setId(area.getId()+ "");  
                indexQuery.setObject(area);  
                indexQuery.setIndexName("elasticsearch");  
                indexQuery.setType("area");  
                queries.add(indexQuery);  
                if (counter % 100 == 0) {  
                	elasticSearchTemplate.bulkIndex(queries);  
                    queries.clear();  
                    System.out.println("bulkIndex counter : " + counter);  
                }  
                counter++;  
            }  
            if (queries.size() > 0) {  
            	elasticSearchTemplate.bulkIndex(queries);  
            }
            long end = System.currentTimeMillis();
            System.out.println("bulkIndex completed use time:"+ (end-start));  
            
        } catch (Exception e) {  
            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());  
            throw e;  
        }  
    } 
}
