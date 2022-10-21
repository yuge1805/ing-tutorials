package com.yuge.ing.validator.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

public enum HeroTypeEnum {

    S(0),
    A(1),
    B(2);

    @Getter
    private Integer value;

    HeroTypeEnum(Integer value) {
        this.value = value;
    }

    public static Optional<HeroTypeEnum> parse(Integer value) {
        for (HeroTypeEnum e : HeroTypeEnum.values()) {
            if (Objects.equals(e.getValue(), value)) {
                return Optional.ofNullable(e);
            }
        }
        return Optional.empty();
    }

}
