package com.jun.biz.common.mybatis.provider;


import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/3/26 15:04
 * <p>
 * Description: [定义基本的增删改查方法]
 * 由{@link GenericMapperProvider}负责实现
 * <p>
 * Company: []
 *
 * 
 */
public interface BaseDao<T, PK extends Serializable> {
    @Options(useGeneratedKeys = true, keyProperty="id", keyColumn="id")
    @InsertProvider(type = GenericMapperProvider.class, method = "insert")
    void insert(T t);

    @DeleteProvider(type = GenericMapperProvider.class, method = "delete")
    void delete(PK pk);

    @UpdateProvider(type = GenericMapperProvider.class, method = "update")
    void update(@Param("domain") T t, @Param("fields") String... fields);

    @SelectProvider(type = GenericMapperProvider.class, method = "selectByPk")
    T selectByPk(PK pk);

    @SelectProvider(type = GenericMapperProvider.class, method = "selectList")
    List<T> selectList(@Param("condition") Map<String, Object> cond, @Param("fields") String... fields);

    @SelectProvider(type = GenericMapperProvider.class, method = "count")
    long count(@Param("condition") Map<String, Object> cond);

    @SelectProvider(type = GenericMapperProvider.class, method = "maxId")
    Long maxId(@Param("condition") Map<String, Object> cond);
}
