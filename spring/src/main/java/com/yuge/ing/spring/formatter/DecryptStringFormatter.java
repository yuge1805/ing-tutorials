package com.yuge.ing.spring.formatter;

import cn.hutool.extra.spring.SpringUtil;
import com.yuge.ing.spring.annotation.Decrypt;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

/**
 * @author: yuge
 * @date: 2024/10/17
 **/
public class DecryptStringFormatter implements Formatter<Object> {

    private Class<?> targetClass;

    private Decrypt annotation;

    public DecryptStringFormatter(Class<?> targetClass, Decrypt annotation) {
        this.targetClass = targetClass;
        this.annotation = annotation;
    }

    @Override
    public Object parse(String text, Locale locale) throws ParseException {
        Object decryptObj = decrypt(text, annotation);
        if (targetClass.isAssignableFrom(decryptObj.getClass())) {
            return decryptObj;
        }
        ConversionService conversionService = SpringUtil.getBean(ConversionService.class);
        TypeDescriptor sourceType = TypeDescriptor.valueOf(String.class);
        TypeDescriptor targetType = TypeDescriptor.valueOf(targetClass);
        return conversionService.convert(decryptObj, sourceType, targetType);
    }

    @Override
    public String print(Object object, Locale locale) {
        return Objects.toString(object);
    }

    private Object decrypt(String text, Decrypt annotation) {
        // todo decrypt
        return "1";
    }

}
