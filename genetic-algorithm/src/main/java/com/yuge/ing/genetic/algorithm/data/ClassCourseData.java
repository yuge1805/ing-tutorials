package com.yuge.ing.genetic.algorithm.data;

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
public class ClassCourseData {

    /**
     * 年级id
     */
    private Long gradeId;

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 老师sequence
     */
    private String teacherSequence;

    /**
     * 老师名称
     */
    private String teacherName;

    public ClassCourseData(String className, String courseName, String teacherName) {
        this.className = className;
        this.courseName = courseName;
        this.teacherName = teacherName;
    }

}
