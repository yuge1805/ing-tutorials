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
 * @date: 2022/10/20
 **/
@Documented
@Constraint(validatedBy = {EnumValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValid {

    /**
     * Enum Class
     *
     * @return
     */
    Class<? extends Enum<?>> clazz();

    /**
     * Enum field
     *
     * @return
     */
    String field() default "";

    String message() default "无效Enum";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
