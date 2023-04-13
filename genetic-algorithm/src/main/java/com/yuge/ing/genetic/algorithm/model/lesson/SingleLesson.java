package com.yuge.ing.genetic.algorithm.model.lesson;

import com.yuge.ing.genetic.algorithm.enums.LessonTypeEnum;
import com.yuge.ing.genetic.algorithm.model.Lesson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 单班课
 *
 * @author: yuge
 * @date: 2022/12/30
 **/
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Accessors(chain = true)
@Deprecated
public class SingleLesson extends Lesson {

    /**
     * 班级id
     */
    private Long classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 老师id
     */
    @Deprecated
    private Long teacherId;

    /**
     * 老师sequence
     */
    private String teacherSequence;

    /**
     * 老师名称
     */
    private String teacherName;

    public SingleLesson(Long classId, String className, Long courseId, String courseName, Long teacherId, String teacherSequence, String teacherName) {
        super();
        this.classId = classId;
        this.className = className;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.teacherSequence = teacherSequence;
        this.teacherName = teacherName;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SingleLesson lesson = (SingleLesson) o;
//        return Objects.equals(getId(), lesson.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId());
//    }

}
