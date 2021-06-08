package com.hope.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 短信相关配置
 *
 * @author aodeng
 */
@Component
@ConfigurationProperties(prefix = "sms")
@Configuration
public class SmsConfig {

    /**
     * 阿里云用户AccessKey AccessKey ID
     */
    public static String accessKeyId;

    /**
     * 阿里云用户AccessKey AccessKey Secret
     */
    public static String accessKeySecret;

    /**
     * 短信模板ID
     */
    public static String templateCode;

    /**
     * 短信签名名称
     */
    public static String signName;

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        SmsConfig.accessKeyId = accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        SmsConfig.accessKeySecret = accessKeySecret;
    }

    public static String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        SmsConfig.templateCode = templateCode;
    }

    public static String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        SmsConfig.signName = signName;
    }
}
