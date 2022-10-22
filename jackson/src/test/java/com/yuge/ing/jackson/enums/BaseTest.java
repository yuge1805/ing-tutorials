package com.yuge.ing.jackson.enums;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: yuge
 * @date: 2022/10/22
 **/
public class BaseTest {

    protected ObjectMapper objectMapper = new ObjectMapper();

    {
        // 将错误枚举值、空字符串 反序列化时转换为 null
        // 此举有利有弊，会吞掉转换异常；
        // DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL默认为禁用
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    }

}
