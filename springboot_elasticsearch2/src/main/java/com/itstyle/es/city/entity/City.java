package com.itstyle.es.city.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * 城市
 */
@Document(indexName="elasticsearch",type="city",indexStoreType="fs",shards=5,replicas=1,refreshInterval="3")
public class City {
	private Long id;
	private String name;
	/** 
     * 地理位置经纬度 
     * lat纬度，lon经度 "40.715,-74.011" 
     */  
	@GeoPointField
    private String location;
	private String introduction;
	
	public City() {
		super();
	}
	
	public City(Long id, String name, String location, String introduction) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.introduction = introduction;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
