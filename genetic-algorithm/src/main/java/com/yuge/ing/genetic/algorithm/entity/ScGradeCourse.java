package com.yuge.ing.genetic.algorithm.entity;

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
public class ScGradeCourse {

    /**
     * 年级id
     */
    private Long gradeId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 每周课时
     */
    private int weekDuration;

    /**
     * 每周连堂课时
     * 一次连堂为1
     */
    private int weekConsecutiveDuration;

    public ScGradeCourse(Long gradeId, Long courseId) {
        this.gradeId = gradeId;
        this.courseId = courseId;
    }
}
