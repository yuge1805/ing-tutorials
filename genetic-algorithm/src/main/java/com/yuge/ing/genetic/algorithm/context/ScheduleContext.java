package com.yuge.ing.genetic.algorithm.context;

import cn.hutool.core.lang.Assert;
import com.yuge.ing.genetic.algorithm.model.Clazz;
import com.yuge.ing.genetic.algorithm.model.Course;
import com.yuge.ing.genetic.algorithm.model.Grade;
import com.yuge.ing.genetic.algorithm.model.Lesson;
import com.yuge.ing.genetic.algorithm.model.Room;
import com.yuge.ing.genetic.algorithm.model.Teacher;
import com.yuge.ing.genetic.algorithm.rule.Rule;
import lombok.Data;

import java.util.List;

/**
 * @author: yuge
 * @date: 2022/12/30
 **/
@Data
public class ScheduleContext extends ChromosomeContext {

    /**
     * 每周上课天数
     */
    private int weekSchoolDayNum;

    /**
     * 每天上课节数
     */
    private int dayLessonNum;

    /**
     * 年级集合
     */
    private List<Grade> gradeList;

    /**
     * 班级集合
     */
    private List<Clazz> classList;

    /**
     * 教室集合
     */
    private List<Room> roomList;

    /**
     * 课程集合
     */
    private List<Course> courseList;

    /**
     * 老师集合
     */
    private List<Teacher> teacherList;

    /**
     * 课集合
     */
    private List<Lesson> lessonList;

    /**
     * 规则集合
     */
    private List<Rule> ruleList;

    public void check() {
        Assert.isTrue(weekSchoolDayNum > 0, () -> new IllegalArgumentException("invalid weekSchoolDayNum!"));
        Assert.isTrue(dayLessonNum > 0, () -> new IllegalArgumentException("invalid dayLessonNum!"));
        Assert.notEmpty(classList, () -> new IllegalArgumentException("classList is empty!"));
        Assert.notEmpty(lessonList, () -> new IllegalArgumentException("classList is empty!"));
    }
}
