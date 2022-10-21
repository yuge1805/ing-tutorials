package com.yuge.ing.validator.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author: yuge
 * @date: 2022/10/10
 **/
@Slf4j
public class JsonValidator implements ConstraintValidator<JsonValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(value);
        } catch (JsonProcessingException e) {
            log.error("parse json error!", e);
            return false;
        }
        if (Objects.isNull(jsonNode)) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(JsonValid constraintAnnotation) {
    }

}
