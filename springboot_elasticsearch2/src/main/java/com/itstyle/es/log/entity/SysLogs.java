package com.itstyle.es.log.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
/**
 * 系统日志
 * 创建者 张志朋
 * 网址 https://blog.52itstyle.com
 * 创建时间	2018年1月22日
 * @Document 参数说明
 * String indexName();//索引库的名称，个人建议以项目的名称命名

 * String type() default "";//类型，个人建议以实体的名称命名

 * short shards() default 5;//默认分区数

 * short replicas() default 1;//每个分区默认的备份数

 * String refreshInterval() default "1s";//刷新间隔

 * String indexStoreType() default "fs";//索引文件存储类型
 * 
 * @Field 参数说明
 * FieldType type() default FieldType.Auto;#自动检测属性的类型
 
 * FieldIndex index() default FieldIndex.analyzed;#默认情况下分词
 
 * DateFormat format() default DateFormat.none;
 
 * String pattern() default "";
 
 * boolean store() default false;#默认情况下不存储原文
 
 * String searchAnalyzer() default "";#指定字段搜索时使用的分词器
 
 * String indexAnalyzer() default "";#指定字段建立索引时指定的分词器
 
 * String[] ignoreFields() default {};#如果某个字段需要被忽略
 
 * boolean includeInParent() default false;
 */
@Document(indexName="elasticsearch",type="sysLog",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class SysLogs implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志id
	 */
	@Id
	private Long id;
	
	/**
	 * 操作用户id
	 */
	private Long userId;
	
	/**
	 * 操作用户
	 */
	private String username;
	
	/**
	 * 操作
	 */
	private String operation;
	
	/**
	 * 方法
	 */
	private String method;
	
	/**
	 * 参数
	 */
	private String params;
	
	/**
	 * 耗时
	 */
	private Long time;
	
	/**
	 * 操作ip地址
	 */
	private String ip;
	
	/**
	 * 创建时间
	 */
	private Timestamp gmtCreate;
	/**
	 * 设备类型
	 */
	private Short deviceType;
	/**
	 * 日志类型
	 */
	private Short logType; 
	/**
	 * 异常信息
	 */
	private String exceptionDetail; 
	/**
	 * 平台
	 */
	private Short platFrom;

	public SysLogs() {
		super();
	}

	public SysLogs(Long id, Long userId, String username,
			String operation, String method, String params, Long time,
			String ip, Timestamp gmtCreate, Short deviceType, Short logType,
			String exceptionDetail, Short platFrom) {
		super();
		this.id = id;
		this.userId = userId;
		this.username = username;
		this.operation = operation;
		this.method = method;
		this.params = params;
		this.time = time;
		this.ip = ip;
		this.gmtCreate = gmtCreate;
		this.deviceType = deviceType;
		this.logType = logType;
		this.exceptionDetail = exceptionDetail;
		this.platFrom = platFrom;
	}
	public SysLogs(long num) {
		this.id = num;
		this.userId = 12L;
		this.username = "张三";
		this.operation = "张三";
		this.method = "方法";
		this.params = "参数";
		this.time = 2L;
		this.ip = "192.168.1.190";
		this.gmtCreate = new Timestamp(new Date().getTime());  
		this.deviceType = 1;
		this.logType = 1;
		this.exceptionDetail = "详细";
		this.platFrom = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Short getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Short deviceType) {
		this.deviceType = deviceType;
	}

	public Short getLogType() {
		return logType;
	}

	public void setLogType(Short logType) {
		this.logType = logType;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}

	public Short getPlatFrom() {
		return platFrom;
	}

	public void setPlatFrom(Short platFrom) {
		this.platFrom = platFrom;
	}
}
