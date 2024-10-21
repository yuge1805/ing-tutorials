package com.yuge.ing.spring.formatter;

import com.yuge.ing.spring.annotation.Decrypt;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: yuge
 * @date: 2024/10/17
 **/
public class DecryptStringFormatterFactory implements AnnotationFormatterFactory<Decrypt> {

    private static final Set<Class<?>> FIELD_TYPES;

    static {
        Set<Class<?>> fieldTypes = new HashSet<>(4);
        fieldTypes.add(Integer.class);
        fieldTypes.add(Long.class);
        fieldTypes.add(String.class);
        FIELD_TYPES = Collections.unmodifiableSet(fieldTypes);
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return FIELD_TYPES;
    }

    @Override
    public Printer<?> getPrinter(Decrypt annotation, Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    @Override
    public Parser<?> getParser(Decrypt annotation, Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    protected Formatter<Object> getFormatter(Decrypt annotation, Class<?> fieldType) {
        DecryptStringFormatter formatter = new DecryptStringFormatter(fieldType, annotation);
        return formatter;
    }

}
