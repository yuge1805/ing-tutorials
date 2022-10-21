package com.yuge.ing.validator;

import com.yuge.ing.validator.dto.HeroCommand;
import com.yuge.ing.validator.enums.CampEnum;
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
 * @date: 2022/10/20
 **/
@Slf4j
public class EnumValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenFieldIsValueAndCorrectValue_thenShouldNotReportConstraintViolations() {
        HeroCommand command = new HeroCommand();
        command.setHeroType(HeroTypeEnum.A.getValue());
        Set<ConstraintViolation<HeroCommand>> violations = validator.validate(command);
        Assertions.assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenFieldIsValueAndWrongValue_thenReportConstraintViolations() {
        HeroCommand command = new HeroCommand();
        command.setHeroType(5);
        Set<ConstraintViolation<HeroCommand>> violations = validator.validate(command);
        Assertions.assertThat(violations.size()).isEqualTo(1);
        Assertions.assertThat(violations).allMatch(violation -> {
            return violation.getPropertyPath().toString().equals("heroType");
        });
        log.info(violations.toString());
    }

    @Test
    public void whenNoFieldAndCorrectName_thenShouldNotReportConstraintViolations() {
        HeroCommand command = new HeroCommand();
        command.setCamp(CampEnum.CHU.name());
        Set<ConstraintViolation<HeroCommand>> violations = validator.validate(command);
        Assertions.assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenNoFieldAndWrongName_thenReportConstraintViolations() {
        HeroCommand command = new HeroCommand();
        command.setCamp("CHUN");
        Set<ConstraintViolation<HeroCommand>> violations = validator.validate(command);
        Assertions.assertThat(violations.size()).isEqualTo(1);
        Assertions.assertThat(violations).allMatch(violation -> {
            return violation.getPropertyPath().toString().equals("camp");
        });
        log.info(violations.toString());
    }



}
