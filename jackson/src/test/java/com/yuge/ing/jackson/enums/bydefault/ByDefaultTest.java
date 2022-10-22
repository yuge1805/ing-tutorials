package com.yuge.ing.jackson.enums.bydefault;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuge.ing.jackson.enums.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author: yuge
 * @date: 2022/10/22
 **/
@Slf4j
public class ByDefaultTest extends BaseTest {

    @Test
    public void whenSerializingEnum_thenCorrectRepresentation() throws JsonProcessingException {
        Org org = new Org("xx Org", RegionEnum.XIAN);
        String json = objectMapper.writeValueAsString(org);
        log.info(json);
        Assertions.assertThat(json).contains(RegionEnum.XIAN.name());
    }

    @Test
    public void whenSerializingNullEnum_thenCorrectRepresentation() throws JsonProcessingException {
        Org org = new Org("xx Org", null);
        String json = objectMapper.writeValueAsString(org);
        log.info(json);
        JsonNode jsonNode = objectMapper.readTree(json);
        Assertions.assertThat(jsonNode.get("region").isNull()).isTrue();
    }

    @Test
    public void whenDeserializingJson_thenCorrectRepresentation() throws JsonProcessingException {
        String json = "{\"name\":\"xx Org\",\"region\":\"XIAN\"}";
        Org org = objectMapper.readValue(json, Org.class);
        log.info(org.toString());
        Assertions.assertThat(org.getRegion()).isEqualTo(RegionEnum.XIAN);
    }

    @Test
    public void whenDeserializingJsonWithEmptyString_thenWrongRepresentation() throws JsonProcessingException {
        String json = "{\"name\":\"xx Org\",\"region\":\"\"}";
        Org org = objectMapper.readValue(json, Org.class);
        log.info(org.toString());
        Assertions.assertThat(org.getRegion()).isNull();
    }

    @Test
    public void whenDeserializingJsonWithNull_thenWrongRepresentation() throws JsonProcessingException {
        String json = "{\"name\":\"xx Org\",\"region\":null}";
        Org org = objectMapper.readValue(json, Org.class);
        log.info(org.toString());
        Assertions.assertThat(org.getRegion()).isNull();
    }
    
    
}
