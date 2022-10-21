package com.yuge.ing.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: yuge
 * @date: 2022/10/10
 **/
@Documented
@Constraint(validatedBy = {JsonValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonValid {

    String message() default "无效json格式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
