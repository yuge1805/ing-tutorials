package com.yuge.ing.jackson.bidirection.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuge
 * @date: 2024/2/22
 **/
public class CustomStudentListSerializer extends StdSerializer<List<Student>> {

    public CustomStudentListSerializer() {
        this(null);
    }

    protected CustomStudentListSerializer(Class<List<Student>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Student> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        List<String> names = new ArrayList<>();
        for (Student t : value) {
            names.add(t.getStudentName());
        }
        gen.writeObject(names);
    }

}
