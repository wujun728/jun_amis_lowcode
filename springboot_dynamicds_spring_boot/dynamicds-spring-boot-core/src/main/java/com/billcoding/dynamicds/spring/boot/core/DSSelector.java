package com.billcoding.dynamicds.spring.boot.core;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DSSelector {
    String value() default "";
}