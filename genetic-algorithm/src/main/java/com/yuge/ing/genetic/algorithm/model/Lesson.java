package com.yuge.ing.genetic.algorithm.model;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import com.yuge.ing.genetic.algorithm.enums.LessonTypeEnum;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 课
 *
 * @author: yuge
 * @date: 2022/12/30
 **/
@Data
@Accessors(chain = true)
public class Lesson {

    private static Long nextId = 0L;

    /**
     * id
     */
    private Long id;

    /**
     * 父id
     */
    @Setter
    private Long parentId;

    /**
     * 课程类型
     */
    @Setter
    private LessonTypeEnum type;

    /**
     * 课程
     */
    private Course course;

    /**
     * 老师集合
     * 一个课存在多个老师（助教）
     */
    private List<Teacher> teacherList;

    /**
     * 班级集合
     * 一个课存在多个班
     */
    private List<Clazz> classList;

    /**
     * 每周课时
     */
    private int duration;

    public Lesson() {
        id = nextId++;
    }
    public static Lesson newLesson(LessonTypeEnum type, Course course, Teacher teacher, Clazz clazz, int duration) {
        Assert.notNull(type, () -> new IllegalArgumentException("type is null!"));
        Assert.notNull(course, () -> new IllegalArgumentException("course is null!"));
        Assert.notNull(teacher, () -> new IllegalArgumentException("teacher is null!"));
        Assert.notNull(clazz, () -> new IllegalArgumentException("clazz is null!"));
        Assert.isTrue(duration > 0, () -> new IllegalArgumentException("invalid duration!"));
        return newLesson(type, course, Lists.newArrayList(teacher), Lists.newArrayList(clazz), duration);
    }

    public static Lesson newLesson(LessonTypeEnum type, Course course, List<Teacher> teacherList, List<Clazz> classList, int duration) {
        Assert.notNull(type, () -> new IllegalArgumentException("type is null!"));
        Assert.notNull(course, () -> new IllegalArgumentException("course is null!"));
        Assert.notEmpty(teacherList, () -> new IllegalArgumentException("teacherList is empty!"));
        Assert.notEmpty(classList, () -> new IllegalArgumentException("classList is empty!"));
        Assert.isTrue(duration > 0, () -> new IllegalArgumentException("invalid duration!"));
        Lesson lesson = new Lesson()
                .setType(type)
                .setCourse(course)
                .setTeacherList(teacherList)
                .setClassList(classList)
                .setDuration(duration);
        return lesson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static void restartId() {
        nextId = 0L;
    }

    /**
     * 是否存在老师重叠
     *
     * @param teacherSequence
     * @return
     */
    public boolean teacherOverlap(String teacherSequence) {
        if (CollectionUtils.isEmpty(teacherList)) {
            return false;
        }
        Optional<Teacher> optional = teacherList.stream().filter(Objects::nonNull)
                .filter(teacher -> Objects.equals(teacher.getTeacherSequence(), teacherSequence))
                .findFirst();
        return optional.isPresent();
    }

    /**
     * 是否存在老师重叠
     *
     * @param lessonList
     * @return
     */
    public boolean teacherOverlap(List<Lesson> lessonList) {
        if (CollectionUtils.isEmpty(teacherList)) {
            return false;
        }
        List<Teacher> lessonsTeacherList = lessonList.stream()
                .filter(Objects::nonNull)
                .map(Lesson::getTeacherList)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return !Collections.disjoint(teacherList, lessonsTeacherList);
    }

    /**
     * 是否存在课程
     *
     * @param courseId
     * @return
     */
    public boolean courseOverlap(Long courseId) {
        if (Objects.isNull(course)) {
            return false;
        }
        return Objects.equals(course.getId(), courseId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
