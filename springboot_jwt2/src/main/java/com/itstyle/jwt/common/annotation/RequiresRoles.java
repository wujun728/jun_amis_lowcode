package com.itstyle.jwt.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRoles {

    /**
     * A single String role name or multiple comma-delimitted role names required in order for the method
     * invocation to be allowed.
     */
    String[] value();

    /**
     * The logical operation for the permission check in case multiple roles are specified. AND is the default
     * @since 1.1.0
     */
    Logical logical() default Logical.OR;
}
