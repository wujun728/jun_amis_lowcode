package com.element.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 生成验证码配置
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.border.color", "cyan");
        // 验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty("kaptcha.textproducer.font.color", "cyan");
        // 设置干扰线
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        // 验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width", "120");
        // 验证码图片高度 默认为50
        properties.setProperty("kaptcha.image.height", "36");
        // 验证码文本字符大小 默认为40
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        // 验证码文本生成器
        properties.setProperty("kaptcha.textproducer.impl", "com.element.config.KaptchaTextCreator");
        // 验证码文本字符间距 默认为2
        properties.setProperty("kaptcha.textproducer.char.space", "3");
        // 验证码文本字符长度 默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");
        // 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
