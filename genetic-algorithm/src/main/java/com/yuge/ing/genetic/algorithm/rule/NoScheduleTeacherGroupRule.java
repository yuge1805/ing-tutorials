package com.yuge.ing.genetic.algorithm.rule;

import lombok.Data;

/**
 * 不排课规则-教师组
 *
 * @author: yuge
 * @date: 2023/1/3
 **/
@Data
public class NoScheduleTeacherGroupRule extends NoScheduleRule {

    /**
     * 教师组id
     */
    private Long teacherGroupId;

}
