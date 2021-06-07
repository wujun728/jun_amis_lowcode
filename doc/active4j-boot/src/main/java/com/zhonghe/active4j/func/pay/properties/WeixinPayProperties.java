package com.zhonghe.active4j.func.pay.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "weixin.pay")
@Data
public class WeixinPayProperties {

	//关联的公众号ID
	private String appId;
	
	//微信支付分配的商户号
	private String mchId;
	
	//回调地址
	private String notifyUrl;
	
	//密钥
	private String key;
	
}
