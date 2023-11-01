package com.yuge.ing.jackson.map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author: yuge
 * @date: 2023/10/20
 **/
@Getter
@Setter
@Accessors(chain = true)
public class GoodVO {

    private String company;

    private Map<String, List<GoodProperty>> properties;

    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class GoodProperty {

        private String propertyCode;

        private String propertyValue;

        private String propertyName;

        public GoodProperty(String propertyCode, String propertyValue, String propertyName) {
            this.propertyCode = propertyCode;
            this.propertyValue = propertyValue;
            this.propertyName = propertyName;
        }

    }

}
