package com.ifast.common.support.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ifast.common.CacheConfiguration;
import com.ifast.common.support.sms.MapSceneRepository;
import com.ifast.common.support.sms.SceneRepository;
import com.ifast.common.support.sms.SmsManager;
import com.ifast.common.support.sms.SmsSender;
import com.ifast.common.support.sms.aliyun.AliyunProperties;
import com.ifast.common.support.sms.aliyun.AliyunSender;
import com.ifast.common.support.sms.zhutong.ZhuTongProperties;
import com.ifast.common.support.sms.zhutong.ZhuTongSender;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *
 * </pre>
 * <small> 2018/8/31 19:37 | Aron</small>
 */
@Configuration
@Slf4j
public class SmsConfiguration {

    @Bean
    MapSceneRepository getSceneRepository(SmsBasicProperties properties) {
        return new MapSceneRepository(properties.getScenes());
    }

    @Bean
    SmsManager smsManager(SmsSender sender, SmsBasicProperties properties, SceneRepository sceneRepository) {
        Cache cache = CacheConfiguration.dynaConfigCache(properties.getCacheKey(), properties.getCodeExpireTime());
        SmsManager smsManager = new SmsManager();
        smsManager.setSmsSender(sender);
        smsManager.setProperties(properties);
        smsManager.setCache(cache);
        smsManager.setSceneRepository(sceneRepository);
        return smsManager;
    }

    @Bean
    @ConditionalOnProperty(prefix = "ifast.sms.zt", name = "passwd")
    @ConditionalOnMissingBean(SmsSender.class)
    SmsSender zhuTongSender(ZhuTongProperties properties) {
        if (log.isDebugEnabled()) {
            log.debug("启用上海助通短信服务");
        }
        SmsSender sender = new ZhuTongSender(properties);
        return sender;
    }

    @Bean
    @ConditionalOnProperty(prefix = "ifast.sms.aliyun", name = "accessKeySecret")
    @ConditionalOnMissingBean(SmsSender.class)
    SmsSender aliyunSender(AliyunProperties properties) {
        if (log.isDebugEnabled()) {
            log.debug("启用阿里云短信服务");
        }
        SmsSender sender = new AliyunSender(properties);
        return sender;
    }

}
