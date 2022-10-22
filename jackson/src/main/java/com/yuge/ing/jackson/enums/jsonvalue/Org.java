package com.yuge.ing.jackson.enums.jsonvalue;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yuge
 * @date: 2022/10/22
 **/
@Data
@NoArgsConstructor
class Org {

    private String name;

    private RegionEnum region;

    public Org(String name, RegionEnum region) {
        this.name = name;
        this.region = region;
    }
}
