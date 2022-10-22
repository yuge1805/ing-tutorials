package com.yuge.ing.jackson.enums.jsonproperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @JsonProperty指定枚举序列化、反序化时，映射为@JsonProperty的值
 * 例：{"name":"xx Org","region": "Region-XIAN"}
 *
 * @author: yuge
 * @date: 2022/10/22
 **/
enum RegionEnum {

    @JsonProperty("Region-XIAN")
    XIAN("西安", 610100),
    @JsonProperty("Region-TAIYUAN")
    TAIYUAN("太原", 140100);

    @Getter
    private String name;

    @Getter
    private Integer code;

    RegionEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

}
