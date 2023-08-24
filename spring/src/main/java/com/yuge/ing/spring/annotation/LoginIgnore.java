package com.yuge.ing.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: yuge
 * @date: 2023/7/5
 **/
@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface LoginIgnore {
}
