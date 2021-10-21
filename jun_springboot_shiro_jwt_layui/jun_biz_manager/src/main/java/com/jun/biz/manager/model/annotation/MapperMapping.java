package com.jun.biz.manager.model.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 2018/3/26 16:23
 * <p>
 * Description: [用于指定表名与数据源]
 * <p>
 *
 * 
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({ TYPE})
public @interface MapperMapping {
    String table();
}
