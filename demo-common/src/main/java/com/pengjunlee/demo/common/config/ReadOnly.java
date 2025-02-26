package com.pengjunlee.demo.common.config;

import java.lang.annotation.*;

/**
 *
 */

@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnly {
}
