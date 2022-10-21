package com.yuge.ing.validator.dto;

import com.yuge.ing.validator.annotation.EnumValid;
import com.yuge.ing.validator.enums.BaseTypeEnum;
import lombok.Data;

/**
 * @author: yuge
 * @date: 2022/10/21
 **/
@Data
public class BaseCommand {

    @EnumValid(clazz = BaseTypeEnum.class, field = "value")
    private int baseInt;

}
