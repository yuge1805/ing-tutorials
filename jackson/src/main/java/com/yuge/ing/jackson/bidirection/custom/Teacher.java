package com.yuge.ing.jackson.bidirection.custom;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author: yuge
 * @date: 2024/2/22
 **/
@Getter
@Setter
@Accessors(chain = true)
class Teacher {

    private String teacherName;

    @JsonSerialize(using = CustomStudentListSerializer.class)
    private List<Student> studentList;

}
