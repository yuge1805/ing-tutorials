package com.yuge.ing.genetic.algorithm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级-课程-老师
 *
 * @author: yuge
 * @date: 2023/1/5
 **/
@Data
@NoArgsConstructor
public class ScClassCourse {

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 老师sequence
     */
    private String teacherSequence;

    public ScClassCourse(Long classId, Long courseId, String teacherSequence) {
        this.classId = classId;
        this.courseId = courseId;
        this.teacherSequence = teacherSequence;
    }

}
