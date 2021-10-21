package com.jun.biz.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import com.jun.biz.common.config.properties.FilePathProperties;
import com.jun.biz.common.image.ImageProcessor;
import com.jun.biz.common.image.impl.ImageProcessorImpl;
import com.jun.biz.common.search.ProductSearcher;
import com.jun.biz.common.service.FileService;
import com.jun.biz.common.service.impl.FileServiceImpl;

/**
 * Created on 2020/11/12 10:46
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@EnableConfigurationProperties(FilePathProperties.class)
@Configuration
public class CommomConfiguration {

    private final FilePathProperties filePathProperties;

    public CommomConfiguration(FilePathProperties filePathProperties) {
        this.filePathProperties = filePathProperties;
    }

    @ConditionalOnClass(ElasticsearchRestTemplate.class)
    @Bean
    public ProductSearcher productSearcher() {
        return new ProductSearcher();
    }

    @ConditionalOnProperty(prefix = "image.processor", name = "path")
    @Bean
    public ImageProcessor imageProcessor() {
        return new ImageProcessorImpl();
    }

    @Bean
    public FileService fileService() {
        return new FileServiceImpl(filePathProperties);
    }
}
