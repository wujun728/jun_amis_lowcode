package com.free.fs.common.config;

import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.free.fs.common.constant.CommonConstant;
import com.free.fs.common.properties.FsServerProperties;
import com.free.fs.common.properties.TenantProperties;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 多租户自动配置
 *
 * @author dinghao
 * @date 2021/3/10
 */
@Configuration
@EnableConfigurationProperties(TenantProperties.class)
public class TenantAutoConfigure {

    @Autowired
    private TenantProperties tenantProperties;

    @Autowired
    private FsServerProperties fsServerProperties;

    @Bean
    public TenantHandler tenantHandler() {
        return new TenantHandler() {
            /**
             * 获取租户id
             */
            @Override
            public Expression getTenantId(boolean where) {
                String tenant = fsServerProperties.getType();
                if (tenant != null) {
                    return new StringValue(tenant);
                }
                return new NullValue();
            }

            /**
             * 获取租户列名
             */
            @Override
            public String getTenantIdColumn() {
                return CommonConstant.SYSTEM_TENANT_ID;
            }

            /**
             * 过滤不需要根据租户隔离的表
             * @param tableName 表名
             */
            @Override
            public boolean doTableFilter(String tableName) {
                return tenantProperties.getIgnoreTables().stream().anyMatch(
                        (e) -> e.equalsIgnoreCase(tableName)
                );
            }
        };
    }

    /**
     * 过滤不需要根据租户隔离的MappedStatement
     */
    @Bean
    public ISqlParserFilter sqlParserFilter() {
        return metaObject -> {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            return tenantProperties.getIgnoreSqls().stream().anyMatch(
                    (e) -> e.equalsIgnoreCase(ms.getId())
            );
        };
    }
}
