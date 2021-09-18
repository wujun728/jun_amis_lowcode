package com.jun.biz.common.mybatis.provider;

import com.google.common.base.CaseFormat;
import com.jun.biz.common.exception.DaoException;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2018/3/26 17:49
 * <p>
 * Description: [通用mapper，此mapper实现了BaseDao里定义的方法，子接口无需在mapper.xml中实现]
 * <p>
 * Company: []
 *
 * 
 */
@Slf4j
public class GenericMapperProvider {


    public static final String ORDER_BY = "orderBy";
    public static final String RESULT_SIZE = "resultSize";
    public static final String BEGIN_INDEX = "beginIndex";
    public static final String NOT_NULL = "@NOT_NULL@";
    public static final String IS_NULL = "@IS_NULL@";



    public static String insert(ProviderContext context, Object domain) {
        final Model model = ModelFieldFactory.getModel(context.getMapperType());
        String tableName = model.getTableName();
        return new SQL() {
            {
                INSERT_INTO(tableName);
                for (Field field : model.getFields()) {
                    String column = field.getColumn();
                    try {
                        Object fieldValue = field.getGetMethod().invoke(domain);
                        if (fieldValue != null) {
                            if (fieldValue instanceof Collection) {
                                log.warn("class [{}] field [{}] is collection, ignore this!", model.getModelClass().getSimpleName(), field.getName());
                                continue;
                            }
                            VALUES(column, "#{" + field.getName() + "}");
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new DaoException(e);
                    }
                }
            }
        }.toString();

    }






    public static String update(ProviderContext context, @Param("domain") Object domain , @Param("fields") String[] fields) {
        final Model model = ModelFieldFactory.getModel(context.getMapperType());
        String tableName = model.getTableName();
        return new SQL() {{
            Set<String>fieldSet = null;
            if(fields.length > 0) {
                fieldSet = new HashSet<>(fields.length);
                Collections.addAll(fieldSet, fields);
            }
            UPDATE(tableName);
            for (Field field : model.getFields()) {
                if (fieldSet != null && !fieldSet.contains(field.getName())) {
                    continue;
                }
                String column = field.getColumn();
                Object fieldValue;
                try {
                    fieldValue = field.getGetMethod().invoke(domain);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new DaoException(e);
                }
                if (fieldValue != null) {
                    SET(column + "=#{domain." + field.getName() + "}");
                }
            }
            WHERE(model.getPk().getColumn() + "=#{domain." + model.getPk().getName() + "}");
        }}.toString();

    }


    public static String selectByPk(ProviderContext context) {
        final Model model = ModelFieldFactory.getModel(context.getMapperType());
        String tableName = model.getTableName();
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE(model.getPk().getColumn() + "=#{" + model.getPk().getName() + "}");
            if (model.getLogicDelete() != null) {
                WHERE(model.getLogicDelete().getColumn() + " != " + model.getLogicDelete().getLogicDelete().deleteValue());
            }

        }}.toString();
    }


    public static String delete(ProviderContext context) {
        final Model model = ModelFieldFactory.getModel(context.getMapperType());
        String tableName = model.getTableName();
        return new SQL() {
            {

                if (model.getLogicDelete() == null) {
                    DELETE_FROM(tableName);
                } else {
                    UPDATE(tableName);
                    SET(model.getLogicDelete().getColumn() + " = " + model.getLogicDelete().getLogicDelete().deleteValue());

                }
                WHERE(model.getPk().getColumn() + "=#{" + model.getPk().getName() + "}");
            }
        }.toString();
    }

    public static String selectList(ProviderContext context, @Param("condition") Map<String, Object> cond, @Param("fields") String[] fields) {
        final Model model = ModelFieldFactory.getModel(context.getMapperType());
        String tableName = model.getTableName();
        StringBuilder sql = new StringBuilder(new SQL() {
            {
                if(fields==null || fields.length == 0 ){
                    SELECT("*");
                }else{
                    SELECT(Arrays.stream(fields).map(s -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s)).collect(Collectors.toList()).toArray(new String[]{}));
                }
                FROM(tableName);
                if (model.getLogicDelete() != null) {
                    WHERE(model.getLogicDelete().getColumn() + " != " + model.getLogicDelete().getLogicDelete().deleteValue());
                }
                fillSqlWhere(cond, this);
                if (cond != null && cond.containsKey(ORDER_BY)) {
                    ORDER_BY(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, String.valueOf(cond.get(ORDER_BY))));
                }
            }
        }.toString());
        if (cond != null && cond.containsKey(RESULT_SIZE)) {
            sql.append(" LIMIT ");
            if (cond.containsKey(BEGIN_INDEX)) {
                sql.append("#{condition.").append(BEGIN_INDEX).append("} , ");
            }
            sql.append("#{condition.").append(RESULT_SIZE).append("}");
        }
        return sql.toString();
    }

    private static void fillSqlWhere(Map<String, Object> cond, SQL sql) {
        if (cond == null) {
            return;
        }
        cond.forEach((key, val) -> {
            if (val == null) {
                return;
            }
            if (BEGIN_INDEX.equals(key) || RESULT_SIZE.equals(key) || ORDER_BY.equals(key)) {
                return;
            }
            if (val instanceof String && "".equals(val)) {
                return;
            }
            boolean not = key.startsWith("not");
            boolean like = key.startsWith("like");
            boolean min = key.startsWith("min");
            boolean max = key.startsWith("max");
            boolean startLike = key.startsWith("startLike");
            boolean endLike = key.startsWith("endLike");
            String column = key;
            if (not) {
                column = key.replaceFirst("not", "");
            } else if (like) {
                column = key.replaceFirst("like", "");
            } else if (startLike) {
                column = key.replaceFirst("startLike", "");
            } else if (endLike) {
                column = key.replaceFirst("endLike", "");
            } else if (min) {
                column = key.replaceFirst("min", "");
            } else if (max) {
                column = key.replaceFirst("max", "");
            }
            column = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, StringUtils.uncapitalize(column));
            if (like) {
                sql.WHERE(column + " LIKE \"%\"#{condition." + key + "}\"%\"");
            } else if (startLike) {
                sql.WHERE(column + " LIKE #{condition." + key + "}\"%\"");
            } else if (endLike) {
                sql.WHERE(column + " LIKE \"%\"#{condition." + key + "}");
            } else if (min) {
                sql.WHERE(column + " > #{condition." + key + "}");
            } else if (max) {
                sql.WHERE(column + " <= #{condition." + key + "}");
            } else {

                if (val instanceof Collection || val.getClass().isArray()) {
                    int valLength;
                    if (val instanceof Collection) {
                        valLength = ((Collection<?>) val).size();
                    } else {
                        valLength = Array.getLength(val);
                    }
                    if (valLength == 0) {
                        return;
                    }
                    StringBuilder inCond = new StringBuilder();
                    inCond.append(column).append(not ? " not " : "").append(" in (");
                    for (int i = 0; i < valLength; i++) {
                        inCond.append("#{condition.").append(key).append("[").append(i).append("]}");
                        if (i < valLength - 1) {
                            inCond.append(" , ");
                        }
                    }
                    inCond.append(" )");
                    sql.WHERE(inCond.toString());
                } else {
                    if (NOT_NULL.equals(val)) {
                        sql.WHERE(column + " is not null ");
                    } else if (IS_NULL.equals(val)) {
                        sql.WHERE(column + " is null ");
                    } else {
                        sql.WHERE(column + (not ? " != " : " = ") + "#{condition." + key + "}");
                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    public static String count(ProviderContext context, Map<String, Object> paramMap) {
        Map<String,Object> cond = (Map<String, Object>) paramMap.get("condition");
        final Model model = ModelFieldFactory.getModel(context.getMapperType());
        String tableName = model.getTableName();
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(tableName);
                if (model.getLogicDelete() != null) {
                    WHERE(model.getLogicDelete().getColumn() + " != " + model.getLogicDelete().getLogicDelete().deleteValue());
                }
                if (cond != null && cond.size() > 0) {
                    fillSqlWhere(cond, this);
                }

            }
        }.toString();
    }

    @SuppressWarnings("unchecked")
    public static String maxId(ProviderContext context, Map<String, Object> paramMap) {
        Map<String,Object> cond = (Map<String, Object>) paramMap.get("condition");

        final Model model = ModelFieldFactory.getModel(context.getMapperType());
        String tableName = model.getTableName();
        return new SQL() {
            {
                SELECT("max(id)");
                FROM(tableName);
                if (model.getLogicDelete() != null) {
                    WHERE(model.getLogicDelete().getColumn() + " != " + model.getLogicDelete().getLogicDelete().deleteValue());
                }
                if (cond != null && cond.size() > 0) {
                    fillSqlWhere(cond, this);
                }

            }
        }.toString();
    }


}
