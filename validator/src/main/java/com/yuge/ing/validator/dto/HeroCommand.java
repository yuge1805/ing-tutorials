package com.yuge.ing.validator.dto;

import com.yuge.ing.validator.annotation.EnumValid;
import com.yuge.ing.validator.enums.CampEnum;
import com.yuge.ing.validator.enums.HeroTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: yuge
 * @date: 2022/10/20
 **/
@Data
@Accessors(chain= true)
public class HeroCommand {

    @EnumValid(clazz = HeroTypeEnum.class, field = "value")
    private Integer heroType;

    @EnumValid(clazz = CampEnum.class)
    private String camp;

}
