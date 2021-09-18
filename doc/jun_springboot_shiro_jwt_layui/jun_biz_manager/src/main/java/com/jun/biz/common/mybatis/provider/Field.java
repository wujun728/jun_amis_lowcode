package com.jun.biz.common.mybatis.provider;

import lombok.Data;

import java.lang.reflect.Method;

import com.jun.biz.manager.model.annotation.LogicDelete;

/**
 * Created on 2019/6/13 10:53
 * <p>
 * Description: [反射字段描述]
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class Field {
    private String name;
    private String column;
    private Method getMethod;
    private LogicDelete logicDelete;
}
