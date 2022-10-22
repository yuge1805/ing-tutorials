package com.yuge.ing.jackson.enums.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author: yuge
 * @date: 2022/10/22
 **/
public class RegionEnumSerializer extends StdSerializer<RegionEnum> {

    public RegionEnumSerializer() {
        super(RegionEnum.class);
    }

    @Override
    public void serialize(RegionEnum regionEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("regionName");
        jsonGenerator.writeString(regionEnum.getName());
        jsonGenerator.writeFieldName("regionCode");
        jsonGenerator.writeNumber(regionEnum.getCode());
        jsonGenerator.writeEndObject();
    }

}
