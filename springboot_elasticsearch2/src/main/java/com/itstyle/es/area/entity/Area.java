package com.itstyle.es.area.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * 区域数据
 */
@Document(indexName="elasticsearch",type="area",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class Area {
	@Id
	private Long id; 
	private Long pid;//父id
	@Field(analyzer="ik",searchAnalyzer="ik")
	private String  shortname;//简称
	@Field(analyzer="ik",searchAnalyzer="ik")
	private String  name;//名称
	@Field(analyzer="ik",searchAnalyzer="ik")
	private String  mergerName;//全称 
	private Short   level; //层级 0 1 2 省市区县
	private String  pinyin;//拼音
	private String  code; //长途区号
	private String  zipCode; //邮编
	private String  first; //首字母
	@GeoPointField
	private String  location;//经纬度
	
	public Area() {
		super();
	}

	public Area(Long id, Long pid, String shortname, String name,
			String mergerName, Short level, String pinyin, String code,
			String zipCode, String first, String location) {
		super();
		this.id = id;
		this.pid = pid;
		this.shortname = shortname;
		this.name = name;
		this.mergerName = mergerName;
		this.level = level;
		this.pinyin = pinyin;
		this.code = code;
		this.zipCode = zipCode;
		this.first = first;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
