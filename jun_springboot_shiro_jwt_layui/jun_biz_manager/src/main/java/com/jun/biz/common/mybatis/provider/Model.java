package com.jun.biz.common.mybatis.provider;

import lombok.Data;

import java.util.Set;

/**
 * Created on 2019/6/13 13:01
 * <p>
 * Description: [反射读取model]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class Model {
    private Set<Field> fields;
    private Class<?> modelClass;
    private String tableName;
    private Field pk;
    private Field logicDelete;
}
