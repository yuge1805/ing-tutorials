package com.yuge.ing.jackson.bidirection.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author: yuge
 * @date: 2024/2/22
 **/
public class ViewSerializationTest {

    protected ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenBidirectionRelation_whenSerializing_thenCorrect() throws JsonProcessingException {
        Student student = new Student()
                .setStudentName("孙悟空");
        Teacher teacher = new Teacher()
                .setTeacherName("菩提老祖");
        student.setTeacherList(Lists.newArrayList(teacher));
        teacher.setStudentList(Lists.newArrayList(student));

        String json = objectMapper
                .writerWithView(Views.Public.class)
                .writeValueAsString(student);
        Assertions.assertTrue(json.contains("孙悟空"));
        System.out.println(json);
    }

}
