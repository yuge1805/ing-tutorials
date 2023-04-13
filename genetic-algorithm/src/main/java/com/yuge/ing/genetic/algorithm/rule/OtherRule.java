package com.yuge.ing.genetic.algorithm.rule;

import com.yuge.ing.genetic.algorithm.enums.TeachDistributionDayEnum;
import com.yuge.ing.genetic.algorithm.enums.TeachDistributionWeekEnum;
import lombok.Data;

/**
 * 其他规则
 *
 * @author: yuge
 * @date: 2023/1/3
 **/
@Data
public class OtherRule extends Rule {

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 周任课规则
     */
    private TeachDistributionWeekEnum teachDistributionWeek;

    /**
     * 日任课规则
     */
    private TeachDistributionDayEnum teachDistributionDay;

}
