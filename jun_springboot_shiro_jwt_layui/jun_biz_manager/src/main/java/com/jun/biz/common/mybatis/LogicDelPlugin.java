package com.jun.biz.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.jun.biz.common.mybatis.provider.Model;
import com.jun.biz.common.mybatis.provider.ModelFieldFactory;
import com.jun.biz.common.utils.StringUtil;

import java.util.Properties;

/**
 * Created on 2020/9/8 10:39
 * <p>
 * Description: [逻辑删除拦截器]
 * <p>
 *
 * 
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
@Slf4j
public class LogicDelPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];

        BoundSql boundSql;
        //由于逻辑关系，只会进入一次
        if (args.length == 4) {
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
        } else {
            //6 个参数时
            boundSql = (BoundSql) args[5];
        }
        String id = ms.getId();
        String mapperClass = id.substring(0, id.lastIndexOf("."));
        Model model = ModelFieldFactory.getModel(Class.forName(mapperClass));
        //未找到逻辑删除字段
        if (model.getLogicDelete() == null) {
            return invocation.proceed();
        }
        String sql = boundSql.getSql().toLowerCase();
        //sql已包含逻辑删除字段
        if (sql.contains(model.getLogicDelete().getColumn())) {
            return invocation.proceed();
        }
        String logicDelSql = model.getTableName() + "." + model.getLogicDelete().getColumn() + " != " + model.getLogicDelete().getLogicDelete().deleteValue();
        if (!sql.contains("where")) {
            //插入到表名后面
            sql = StringUtil.insert(sql, model.getTableName(), " where " + logicDelSql);
        } else {
            //插入到where后面
            sql = StringUtil.insert(sql, "where", " " + logicDelSql + " and ");
        }

        // 重新new一个查询语句对像
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        // 把新的查询放到statement里
        MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        args[0] = newMs;
        return invocation.proceed();
    }


    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(ms.getKeyProperties()[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("设置properties：{}", properties);
    }



    public static class BoundSqlSqlSource implements SqlSource {
        private final BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
