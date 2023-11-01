package com.yuge.ing.jackson.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.yuge.ing.jackson.enums.BaseTest;
import com.yuge.ing.jackson.map.GoodVO.GoodProperty;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GoodVOTest extends BaseTest {

    @Test
    public void printSerializingMap() throws JsonProcessingException {
        Map<String, List<GoodProperty>> properties = Maps.newHashMap();
        properties.put("111", Lists.newArrayList(new GoodProperty("1a", "1b", "1c")));
        properties.put("222", Lists.newArrayList(new GoodProperty("2a", "2b", "2c")));
        properties.put("333", Lists.newArrayList(new GoodProperty("3a", "3b", "3c")));
        GoodVO goodVO = new GoodVO();
        goodVO.setCompany("qn");
        goodVO.setProperties(properties);
        String json = objectMapper.writeValueAsString(goodVO);
        log.info(json);
    }

    @Test
    public void whenDeserializingJsonToMap_thenCorrect() throws JsonProcessingException {
        String json = "{\"company\":\"qn\",\"properties\":{\"111\":[{\"propertyCode\":\"1a\",\"propertyValue\":\"1b\",\"propertyName\":\"1c\"}],\"222\":[{\"propertyCode\":\"2a\",\"propertyValue\":\"2b\",\"propertyName\":\"2c\"}],\"333\":[{\"propertyCode\":\"3a\",\"propertyValue\":\"3b\",\"propertyName\":\"3c\"}]}}";
        GoodVO goodVO = objectMapper.readValue(json, GoodVO.class);
        Assertions.assertNotNull(goodVO);
        Assertions.assertNotNull(goodVO.getProperties());
        Assertions.assertTrue(!goodVO.getProperties().isEmpty());
    }

}