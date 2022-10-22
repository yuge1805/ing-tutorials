package com.yuge.ing.jackson.enums.jsoncreator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @JsonCreator\@JsonFormat
 * 例：{"name":"xx Org","region":{"name":"西安", "code":610100}}
 *
 * 反序列化场景：
 * @JsonCreator
 *
 * 序列化场景：
 * @JsonFormat (shape = JsonFormat.Shape.OBJECT)
 * 从 Jackson 2.1.2开始，将枚举序列化为对象
 *
 * @author: yuge
 * @date: 2022/10/22
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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

    @JsonCreator
    public static RegionEnum forValues(@JsonProperty("name") String name, @JsonProperty("code") Integer code) {
        for (RegionEnum regionEnum : RegionEnum.values()) {
            if (regionEnum.getName().equals(name) && regionEnum.getCode().equals(code)) {
                return regionEnum;
            }
        }
        return null;
    }

}
