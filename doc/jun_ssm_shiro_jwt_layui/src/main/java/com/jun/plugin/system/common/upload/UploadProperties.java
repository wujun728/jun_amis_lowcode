package com.jun.plugin.system.common.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * ClassName: UploadProperties Description: layui date: 2020/4/13 19:48
 *
 * 
 * 
 * @since JDK 1.8 fastdfs文件上传配置类
 */
// 配置属性
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {

	/**
	 * 文件路径
	 */
	private String baseUrl;
	/**
	 * 多个文件格式
	 */
	private List<String> allowTypes;
}
