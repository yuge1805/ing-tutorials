package com.yuge.ing.genetic.algorithm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级
 *
 * @author: yuge
 * @date: 2022/12/29
 **/
@Data
@NoArgsConstructor
public class Clazz {

    /**
     * id
     */
    private Long id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级id
     */
    private Long gradeId;

    public Clazz(Long id, String className, Long gradeId) {
        this.id = id;
        this.className = className;
        this.gradeId = gradeId;
    }
}
