package com.yuge.ing.validator;

import com.yuge.ing.validator.dto.BaseCommand;
import com.yuge.ing.validator.dto.BaseCommand;
import com.yuge.ing.validator.enums.BaseTypeEnum;
import com.yuge.ing.validator.enums.HeroTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author: yuge
 * @date: 2022/10/21
 **/
@Slf4j
public class BaseTypeValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenFieldIsValueAndCorrectValue_thenShouldNotReportConstraintViolations() {
        BaseCommand command = new BaseCommand();
        command.setBaseInt(BaseTypeEnum.S.getValue());
        Set<ConstraintViolation<BaseCommand>> violations = validator.validate(command);
        Assertions.assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenFieldIsValueAndWrongValue_thenReportConstraintViolations() {
        BaseCommand command = new BaseCommand();
        command.setBaseInt(5);
        Set<ConstraintViolation<BaseCommand>> violations = validator.validate(command);
        Assertions.assertThat(violations.size()).isEqualTo(1);
        Assertions.assertThat(violations).allMatch(violation -> {
            return violation.getPropertyPath().toString().equals("baseInt");
        });
        log.info(violations.toString());
    }    


}
