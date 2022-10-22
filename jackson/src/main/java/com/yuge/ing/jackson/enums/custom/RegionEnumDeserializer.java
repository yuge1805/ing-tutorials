package com.yuge.ing.jackson.enums.custom;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Objects;

/**
 * @author: yuge
 * @date: 2022/10/22
 **/
public class RegionEnumDeserializer extends StdDeserializer<RegionEnum> {

    public RegionEnumDeserializer() {
        super(Object.class);
    }

    public RegionEnumDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RegionEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode jsonNode = p.getCodec().readTree(p);
        String regionName = jsonNode.get("regionName").asText();
        int regionCode = jsonNode.get("regionCode").asInt();
        for (RegionEnum regionEnum : RegionEnum.values()) {
            if (Objects.equals(regionCode, regionEnum.getCode())
                    && Objects.equals(regionName, regionEnum.getName())) {
                return regionEnum;
            }
        }
        return null;
    }
}
