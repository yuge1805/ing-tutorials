package com.yuge.ing.genetic.algorithm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 年级
 *
 * @author: yuge
 * @date: 2023/1/5
 **/
@Data
@NoArgsConstructor
public class Grade {

    /**
     * 年级id
     */
    private Long id;

    /**
     * 年级名称
     */
    private String gradeName;

    public Grade(Long id, String gradeName) {
        this.id = id;
        this.gradeName = gradeName;
    }

}
