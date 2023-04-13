package com.yuge.ing.genetic.algorithm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 老师
 *
 * @author: yuge
 * @date: 2022/12/29
 **/
@Data
@NoArgsConstructor
public class Teacher {

    /**
     * id
     */
    private Long id;

    /**
     * 老师sequence
     */
    private String teacherSequence;

    /**
     * 老师姓名
     */
    private String teacherName;

    public Teacher(Long id, String teacherSequence, String teacherName) {
        this.id = id;
        this.teacherSequence = teacherSequence;
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherSequence, teacher.teacherSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherSequence);
    }
}
