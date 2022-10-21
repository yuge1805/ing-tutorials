package com.yuge.ing.validator.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

public enum BaseTypeEnum {

    S(0),
    A(1),
    B(2);

    @Getter
    private int value;

    BaseTypeEnum(int value) {
        this.value = value;
    }

    public static Optional<BaseTypeEnum> parse(int value) {
        for (BaseTypeEnum e : BaseTypeEnum.values()) {
            if (Objects.equals(e.getValue(), value)) {
                return Optional.ofNullable(e);
            }
        }
        return Optional.empty();
    }

}
