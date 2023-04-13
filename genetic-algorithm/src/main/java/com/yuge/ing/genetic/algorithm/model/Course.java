package com.yuge.ing.genetic.algorithm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程
 *
 * @author: yuge
 * @date: 2022/12/29
 **/
@Data
@NoArgsConstructor
public class Course {

    /**
     * 课程id
     */
    private Long id;

    /**
     * 课程名
     */
    private String courseName;

    public Course(Long id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

}
