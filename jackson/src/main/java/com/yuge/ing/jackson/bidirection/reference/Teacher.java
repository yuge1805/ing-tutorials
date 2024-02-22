package com.yuge.ing.jackson.bidirection.reference;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    private List<Student> studentList;

}
