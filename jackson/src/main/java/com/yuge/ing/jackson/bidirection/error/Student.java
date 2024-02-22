package com.yuge.ing.jackson.bidirection.error;

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
class Student {

    private String studentName;

    private List<Teacher> teacherList;

}
