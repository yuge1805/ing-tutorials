package com.yuge.ing.jackson.enums.jsonvalue;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @JsonValue指定枚举序列化、反序化时的具体属性
 * 例：{"name":"xx Org","region": 140100}
 *
 * @author: yuge
 * @date: 2022/10/22
 **/
enum RegionEnum {

    XIAN("西安", 610100),
    TAIYUAN("太原", 140100);

    @Getter
    private String name;

    @JsonValue
    @Getter
    private Integer code;

    RegionEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

}
