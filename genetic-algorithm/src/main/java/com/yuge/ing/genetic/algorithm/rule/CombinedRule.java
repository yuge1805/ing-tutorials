package com.yuge.ing.genetic.algorithm.rule;

import lombok.Data;

import java.util.List;

/**
 * 合班规则
 *
 * @author: yuge
 * @date: 2023/1/7
 **/
@Data
public class CombinedRule extends Rule {

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 班级id集合
     */
    private List<Long> classIdList;

}
