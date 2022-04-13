package com.ruoyi.framework.config.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * swagger 配置属性
 *
 * @author Lion Li
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /**
     * 文档开关
     */
    private Boolean enabled;

    /**
     * 设置请求的统一前缀
     */
    private String pathMapping;

    /**
     * 标头
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 项目版本
     */
    private String version;

    /**
     * 联系方式
     */
    private Contact contact;

    /**
     * 组配置
     */
    private List<Groups> groups;

    @Data
    @NoArgsConstructor
    public static class Contact {

        /**
         * 联系人
         */
        private String name;

        /**
         * 联系人url
         */
        private String url;

        /**
         * 联系人email
         */
        private String email;

    }

    @Data
    @NoArgsConstructor
    public static class Groups {

        /**
         * 组名
         */
        private String name;

        /**
         * 基础包路径
         */
        private String basePackage;

    }

}
