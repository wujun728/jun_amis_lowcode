package com.jun.biz.common.search.document;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * 
 */
@Data
@Document(indexName = "product")
public class ProductDoc {
	@Id
	private Long id;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String name;
	@Field(type = FieldType.Double)
	private double price;
	@Field(type = FieldType.Double)
	private double originalPrice;
	@Field(index = false, type = FieldType.Keyword)
	private String mainPic;
	@Field  
	private Date createTime;
	@Field
	private int salesNum;
	@Field
	private double evaluationScore;
	@Field
	private int evaluationNum;
	@Field
	private List<String> categoryNames;
	@Field
	private List<Long> categoryIds;

	
	 
}
