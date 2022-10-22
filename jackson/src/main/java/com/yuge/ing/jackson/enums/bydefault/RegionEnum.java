package com.yuge.ing.jackson.enums.bydefault;

import lombok.Getter;

/**
 * 默认枚举值的序列化、反序列化为name()
 * 例：{"name":"xx Org","region":"XIAN"}
 *
 * @author: yuge
 * @date: 2022/10/22
 **/
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
