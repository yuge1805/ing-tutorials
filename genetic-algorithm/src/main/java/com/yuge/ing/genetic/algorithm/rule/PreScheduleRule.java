package com.yuge.ing.genetic.algorithm.rule;

import com.yuge.ing.genetic.algorithm.enums.RuleTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

/**
 * 预排规则
 *
 * @author: yuge
 * @date: 2023/1/3
 **/
@Data
@NoArgsConstructor
public class PreScheduleRule extends Rule {

    /**
     * 周几
     */
    private DayOfWeek weekDay;

    /**
     * 第几节
     */
    private Integer section;

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

    public PreScheduleRule(DayOfWeek weekDay, Integer section, Long classId, Long courseId, String teacherSequence) {
        this.weekDay = weekDay;
        this.section = section;
        this.classId = classId;
        this.courseId = courseId;
        this.teacherSequence = teacherSequence;
    }

}
