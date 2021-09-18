package com.jun.biz.manager.model.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.jun.biz.manager.model.enums.DelStatus;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 2019-6-13 11:21:33
 * <p>
 * Description: [逻辑删除注解]
 * <p>
 *
 * 
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({FIELD})
public @interface LogicDelete {

    int deleteValue() default DelStatus.DEL_VALUE;

}
