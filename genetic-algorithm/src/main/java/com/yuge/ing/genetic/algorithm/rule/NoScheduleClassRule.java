package com.yuge.ing.genetic.algorithm.rule;

import lombok.Data;

/**
 * 不排课规则-班级
 *
 * @author: yuge
 * @date: 2023/1/3
 **/
@Data
public class NoScheduleClassRule extends NoScheduleRule {

    /**
     * 班级id
     */
    private Long classId;

}
