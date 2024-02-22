package com.yuge.ing.jackson.bidirection.view;

import com.fasterxml.jackson.annotation.JsonView;
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

    @JsonView(Views.Public.class)
    private String teacherName;

    @JsonView(Views.All.class)
    private List<Student> studentList;

}
