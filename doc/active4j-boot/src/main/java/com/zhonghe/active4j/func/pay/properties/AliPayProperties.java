package com.zhonghe.active4j.func.pay.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "ali.pay")
@Data
public class AliPayProperties {

	//关联的公众号ID
	private String appId;
	
	//请求私钥
	private String privateKey;
	
	//请求公钥
	private String publicKey;
	
	//页面跳转同步通知页面路径
	private String returnUrl;
	
	//服务器异步通知
	private String notifyUrl;
	
	private String signType;
	
	private String charset;
	
	private String aliPublicKey;
	
}
