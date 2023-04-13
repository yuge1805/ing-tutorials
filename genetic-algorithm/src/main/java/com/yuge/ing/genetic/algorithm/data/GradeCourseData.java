package com.yuge.ing.genetic.algorithm.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 年级-课程
 *
 * @author: yuge
 * @date: 2023/1/5
 **/
@Data
@NoArgsConstructor
public class GradeCourseData {

    /**
     * 年级id
     */
    private Long gradeId;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 每周课时
     */
    private int weekDuration;

    /**
     * 每周连堂课时
     * 一次连堂为1
     */
    private int weekConsecutiveDuration;

    public GradeCourseData(String gradeName, String courseName, int weekDuration) {
        this.gradeName = gradeName;
        this.courseName = courseName;
        this.weekDuration = weekDuration;
    }

    public GradeCourseData(String gradeName, String courseName, int weekDuration, int weekConsecutiveDuration) {
        this.gradeName = gradeName;
        this.courseName = courseName;
        this.weekDuration = weekDuration;
        this.weekConsecutiveDuration = weekConsecutiveDuration;
    }

}
