package com.yuge.ing.jackson.enums.custom;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

/**
 * 自定义场景
 * 例：{"name":"xx Org","region":{"regionName":"西安","regionCode":610100}}
 *
 * @author: yuge
 * @date: 2022/10/22
 **/
@JsonSerialize(using = RegionEnumSerializer.class)
@JsonDeserialize(using = RegionEnumDeserializer.class)
enum RegionEnum {

    XIAN("西安", 610100),
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
