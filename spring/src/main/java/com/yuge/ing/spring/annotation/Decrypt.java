package com.yuge.ing.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: yuge
 * @date: 2024/10/10
 **/
@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER})
public @interface Decrypt {

}
