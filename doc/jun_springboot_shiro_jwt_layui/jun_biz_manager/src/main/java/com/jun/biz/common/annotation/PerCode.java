package com.jun.biz.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface PerCode {
    String[] value();
}
