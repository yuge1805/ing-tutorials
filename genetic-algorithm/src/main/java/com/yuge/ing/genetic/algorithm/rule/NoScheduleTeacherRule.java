package com.yuge.ing.genetic.algorithm.rule;

import lombok.Data;

/**
 * 不排课规则-教师
 *
 * @author: yuge
 * @date: 2023/1/3
 **/
@Data
public class NoScheduleTeacherRule extends NoScheduleRule {

    /**
     * 教师sequence
     */
    private String teacherSequence;

}
