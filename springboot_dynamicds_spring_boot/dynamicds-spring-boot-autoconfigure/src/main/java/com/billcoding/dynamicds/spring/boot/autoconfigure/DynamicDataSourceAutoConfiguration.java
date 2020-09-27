package com.billcoding.dynamicds.spring.boot.autoconfigure;


import com.billcoding.dynamicds.spring.boot.core.DynamicDataSource;
import com.billcoding.dynamicds.spring.boot.core.DynamicDataSourceAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(prefix = "dynamicds", name = "enable", havingValue = "true")
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceAutoConfiguration {
    private final DynamicDataSourceProperties properties;

    public DynamicDataSourceAutoConfiguration(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        Map<String, DataSourceProperties> datasource = properties.getDatasource();
        datasource.forEach((name, dsp) -> {
            DataSource ds = DataSourceBuilder.create()
                    .username(dsp.getUsername())
                    .password(dsp.getPassword())
                    .url(dsp.getUrl())
                    .driverClassName(dsp.getDriverClassName())
                    .build();
            targetDataSources.put(name, ds);
        });
        DataSource masterDs = (DataSource) targetDataSources.get(properties.getMaster());
        return new DynamicDataSource(masterDs, targetDataSources);
    }

    @Bean
    @ConditionalOnMissingBean
    DynamicDataSourceAspect dynamicDataSourceAspect() {
        return new DynamicDataSourceAspect();
    }

}
