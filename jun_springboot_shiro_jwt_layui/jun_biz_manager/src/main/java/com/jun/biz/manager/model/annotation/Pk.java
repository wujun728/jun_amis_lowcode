package com.jun.biz.manager.model.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created on 2019-6-13 11:21:49
 * <p>
 * Description: [主键]
 * <p>
 *
 * 
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({FIELD})
public @interface Pk {
}
