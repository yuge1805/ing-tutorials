package com.yuge.ing.genetic.algorithm.rule;

import lombok.Data;

import java.time.DayOfWeek;

/**
 * 不排课规则
 *
 * @author: yuge
 * @date: 2023/1/3
 **/
@Data
public abstract class NoScheduleRule extends Rule {

    /**
     * 周几
     */
    private DayOfWeek weekDay;

    /**
     * 第几节
     */
    private Integer section;

}
