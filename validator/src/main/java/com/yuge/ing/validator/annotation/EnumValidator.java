package com.yuge.ing.validator.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author: yuge
 * @date: 2022/10/20
 **/
@Slf4j
public class EnumValidator implements ConstraintValidator<EnumValid, Object> {

    private Class<? extends Enum<?>> clazz;

    private String field;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        if (value instanceof String
                && ((String) value).isEmpty()) {
            return true;
        }
        Enum<?>[] enumConstants = clazz.getEnumConstants();
        if (Objects.isNull(enumConstants)) {
            throw new IllegalArgumentException(String.format("Failed to obtain the elements of this enum class,  class: %s", clazz.getName()));
        }
        // 未声明字段时，默认校验匹配name
        if (Objects.isNull(field) || field.isEmpty()) {
            return Arrays.stream(enumConstants).anyMatch(anEnum -> anEnum.name().equals(value));
        }
        // 校验字段值
        return Arrays.stream(enumConstants).anyMatch(anEnum -> {
            Field field = ReflectionUtils.findField(clazz, this.field);
            field.setAccessible(true);
            Object o = ReflectionUtils.getField(field, anEnum);
            return Objects.equals(value, o);
        });
    }

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        if (constraintAnnotation.clazz() == null) {
            throw new IllegalArgumentException("The parameter clazz must not be null.");
        }
        this.clazz = constraintAnnotation.clazz();
        this.field = constraintAnnotation.field();
    }
}
