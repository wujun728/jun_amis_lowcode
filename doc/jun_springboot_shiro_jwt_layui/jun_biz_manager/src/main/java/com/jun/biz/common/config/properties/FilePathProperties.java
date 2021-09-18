package com.jun.biz.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2020/10/26 18:59
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Data
@ConfigurationProperties(prefix = FilePathProperties.PREFIX)
public class FilePathProperties {
    public static final String PREFIX = "mall.file";

    private String basePath;
    private String tempPath;
    private String adPath;
    private Product product;
    private String domain;

    @Data
    public static class Product {
        private String path;
        private int picWidth;
        private int picHeight;

    }
}
