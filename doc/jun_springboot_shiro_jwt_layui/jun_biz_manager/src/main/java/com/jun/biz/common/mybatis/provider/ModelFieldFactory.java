package com.jun.biz.common.mybatis.provider;

import com.google.common.base.CaseFormat;
import com.jun.biz.common.exception.DaoException;
import com.jun.biz.manager.model.annotation.IgnoreField;
import com.jun.biz.manager.model.annotation.LogicDelete;
import com.jun.biz.manager.model.annotation.MapperMapping;
import com.jun.biz.manager.model.annotation.Pk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2020/9/8 14:08
 * <p>
 * Description: [TODO]
 * <p>
 * Company: []
 *
 * 
 */
@Slf4j
public class ModelFieldFactory {
    private static final Map<Class<?>, Model> MODEL_CACHE = new ConcurrentHashMap<>(32);

    public static Model getModel(Class<?> clazz) {
        return MODEL_CACHE.computeIfAbsent(clazz, ModelFieldFactory::readModel);
    }

    private static Model readModel(Class<?> daoClass) {
        log.info("反射读取{}实体类属性", daoClass);
        Class<?> domainClazz = ResolvableType.forClass(daoClass).getInterfaces()[0].resolveGeneric(0);
        Objects.requireNonNull(domainClazz, "获取dao泛型失败！");
        Model model = new Model();
        model.setModelClass(domainClazz);
        model.setTableName(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, domainClazz.getSimpleName()));
        MapperMapping mapperMapping = daoClass.getAnnotation(MapperMapping.class);
        if (mapperMapping != null) {
            model.setTableName(mapperMapping.table());
        }

        Set<Field> fields = new HashSet<>(16);

        List<java.lang.reflect.Field> classFields = getClassAllFields(domainClazz);
        for (java.lang.reflect.Field classField : classFields) {
            if (classField.getAnnotation(IgnoreField.class) != null) {
                continue;
            }
            Field field = new Field();
            try {
                field.setGetMethod(domainClazz.getMethod("get" + StringUtils.capitalize(classField.getName())));
            } catch (NoSuchMethodException e) {
                throw new DaoException(e);
            }
            field.setName(classField.getName());
            field.setColumn(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()));
            Pk pk = classField.getAnnotation(Pk.class);
            if (pk != null) {
                model.setPk(field);
            }
            LogicDelete logicDelete = classField.getAnnotation(LogicDelete.class);
            if (logicDelete != null) {
                field.setLogicDelete(logicDelete);
                model.setLogicDelete(field);
            }
            fields.add(field);
        }
        model.setFields(fields);
        log.info("读取{}实体类属性结束", daoClass);
        return model;
    }


    private static List<java.lang.reflect.Field> getClassAllFields(Class<?> clazz) {
        List<java.lang.reflect.Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }
}
