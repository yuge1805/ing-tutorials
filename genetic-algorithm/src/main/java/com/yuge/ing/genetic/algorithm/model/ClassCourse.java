package com.yuge.ing.genetic.algorithm.model;

import lombok.Data;

/**
 * @author: yuge
 * @date: 2022/12/29
 **/
@Data
public class ClassCourse {

    private static Long nextId = 0L;

    /**
     * id
     */
    private Long id;

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 老师id
     */
    private Long teacherId;

    /**
     * 课时
     */
    private int duration;

    public static void restartId() {
        nextId = 0L;
    }

}
