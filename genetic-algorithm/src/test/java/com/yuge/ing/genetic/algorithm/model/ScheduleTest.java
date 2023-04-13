package com.yuge.ing.genetic.algorithm.model;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import com.yuge.ing.genetic.algorithm.context.ScheduleContext;
import com.yuge.ing.genetic.algorithm.data.ClassCourseData;
import com.yuge.ing.genetic.algorithm.data.GradeCourseData;
import com.yuge.ing.genetic.algorithm.enums.LessonTypeEnum;
import com.yuge.ing.genetic.algorithm.rule.PreScheduleRule;
import com.yuge.ing.genetic.algorithm.rule.Rule;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ScheduleTest {

    @Test
    void makeFromConfig() {
        ScheduleContext config = newConfig();
        Schedule schedule = new Schedule(config);
        schedule.initGeneListFromContext();
        print(schedule);
    }

    private ScheduleContext newConfig() {
        ScheduleContext config = new ScheduleContext();
        config.setWeekSchoolDayNum(5);
        config.setDayLessonNum(7);
        config.setGradeList(getGradeList());
        config.setClassList(getClassList());
        config.setRoomList(getRoomList());
        config.setCourseList(getCourseList());
        config.setTeacherList(getTeacherList());

        List<Rule> ruleList = Lists.newArrayList(
                new PreScheduleRule(DayOfWeek.MONDAY, 1, 11L, 1L, "1"),
                new PreScheduleRule(DayOfWeek.MONDAY, 2, 11L, 2L, "2"),
                new PreScheduleRule(DayOfWeek.MONDAY, 2, 12L, 1L, "1")
        );
        config.setRuleList(ruleList);

        // 基础数据
        List<GradeCourseData> gradeCourseDataList = Lists.newArrayList(
                new GradeCourseData("一年级", "语文", 6),
                new GradeCourseData("一年级", "数学", 6),
                new GradeCourseData("一年级", "英语", 6),
                new GradeCourseData("一年级", "物理", 3),
                new GradeCourseData("一年级", "化学", 3),
                new GradeCourseData("一年级", "生物", 3),
                new GradeCourseData("一年级", "历史", 3),
                new GradeCourseData("一年级", "体育", 5),

                new GradeCourseData("二年级", "语文", 6),
                new GradeCourseData("二年级", "数学", 6),
                new GradeCourseData("二年级", "英语", 6),
                new GradeCourseData("二年级", "物理", 3),
                new GradeCourseData("二年级", "化学", 3),
                new GradeCourseData("二年级", "生物", 3),
                new GradeCourseData("二年级", "历史", 3),
                new GradeCourseData("二年级", "体育", 5)
        );
        List<ClassCourseData> classCourseDataList = Lists.newArrayList(
                new ClassCourseData("一年级1班", "语文", "刘邦"),
                new ClassCourseData("一年级1班", "数学", "韩信"),
                new ClassCourseData("一年级1班", "英语", "萧何"),
                new ClassCourseData("一年级1班", "物理", "项羽"),
                new ClassCourseData("一年级1班", "化学", "范增"),
                new ClassCourseData("一年级1班", "生物", "龙且"),
                new ClassCourseData("一年级1班", "历史", "子婴"),
                new ClassCourseData("一年级1班", "体育", "幼公主"),

                new ClassCourseData("一年级2班", "语文", "刘邦"),
                new ClassCourseData("一年级2班", "数学", "韩信"),
                new ClassCourseData("一年级2班", "英语", "萧何"),
                new ClassCourseData("一年级2班", "物理", "项羽"),
                new ClassCourseData("一年级2班", "化学", "范增"),
                new ClassCourseData("一年级2班", "生物", "龙且"),
                new ClassCourseData("一年级2班", "历史", "子婴"),
                new ClassCourseData("一年级2班", "体育", "幼公主"),

                new ClassCourseData("一年级3班", "语文", "刘邦"),
                new ClassCourseData("一年级3班", "数学", "韩信"),
                new ClassCourseData("一年级3班", "英语", "萧何"),
                new ClassCourseData("一年级3班", "物理", "项羽"),
                new ClassCourseData("一年级3班", "化学", "范增"),
                new ClassCourseData("一年级3班", "生物", "龙且"),
                new ClassCourseData("一年级3班", "历史", "子婴"),
                new ClassCourseData("一年级3班", "体育", "幼公主"),

                new ClassCourseData("二年级1班", "语文", "刘邦"),
                new ClassCourseData("二年级1班", "数学", "韩信"),
                new ClassCourseData("二年级1班", "英语", "萧何"),
                new ClassCourseData("二年级1班", "物理", "项羽"),
                new ClassCourseData("二年级1班", "化学", "范增"),
                new ClassCourseData("二年级1班", "生物", "龙且"),
                new ClassCourseData("二年级1班", "历史", "子婴"),
                new ClassCourseData("二年级1班", "体育", "幼公主"),

                new ClassCourseData("二年级2班", "语文", "刘邦"),
                new ClassCourseData("二年级2班", "数学", "韩信"),
                new ClassCourseData("二年级2班", "英语", "萧何"),
                new ClassCourseData("二年级2班", "物理", "项羽"),
                new ClassCourseData("二年级2班", "化学", "范增"),
                new ClassCourseData("二年级2班", "生物", "龙且"),
                new ClassCourseData("二年级2班", "历史", "子婴"),
                new ClassCourseData("二年级2班", "体育", "幼公主")
        );
        List<Lesson> lessonList = getLessonList(config, gradeCourseDataList, classCourseDataList);
        config.setLessonList(lessonList);

        return config;
    }

    private static List<Grade> getGradeList() {
        List<Grade> gradeList = Lists.newArrayList(
                new Grade(1L, "一年级"),
                new Grade(2L, "二年级")
        );
        return gradeList;
    }

    private static List<Clazz> getClassList() {
        List<Clazz> classList = Lists.newArrayList(
                new Clazz(11L, "一年级1班", 1L),
                new Clazz(12L, "一年级2班", 1L),
                new Clazz(13L, "一年级3班", 1L),
                new Clazz(21L, "二年级1班", 2L),
                new Clazz(22L, "二年级2班", 2L),
                new Clazz(23L, "二年级3班", 2L)
        );
        return classList;
    }

    private static List<Room> getRoomList() {
        List<Room> roomList = Lists.newArrayList(
                new Room(11L, "一年级1班", 11L),
                new Room(12L, "一年级2班", 12L),
                new Room(13L, "一年级3班", 13L),
                new Room(21L, "二年级1班", 21L),
                new Room(22L, "二年级2班", 22L),
                new Room(23L, "二年级3班", 23L)
        );
        return roomList;
    }

    private static List<Course> getCourseList() {
        List<Course> courseList = Lists.newArrayList(
                new Course(1L, "语文"),
                new Course(2L, "数学"),
                new Course(3L, "英语"),
                new Course(4L, "物理"),
                new Course(5L, "化学"),
                new Course(6L, "生物"),
                new Course(7L, "历史"),
                new Course(8L, "体育")
        );
        return courseList;
    }

    private static List<Teacher> getTeacherList() {
        List<Teacher> teacherList = Lists.newArrayList(
                new Teacher(1L, "1", "刘邦"),
                new Teacher(2L, "2", "韩信"),
                new Teacher(3L, "3", "萧何"),
                new Teacher(4L, "4", "项羽"),
                new Teacher(5L, "5", "范增"),
                new Teacher(6L, "6", "龙且"),
                new Teacher(7L, "7", "子婴"),
                new Teacher(8L, "8", "幼公主")
        );
        return teacherList;
    }


    private static List<Lesson> getLessonList(ScheduleContext config, List<GradeCourseData> gradeCourseDataList, List<ClassCourseData> classCourseDataList) {
        Map<String, Grade> gradeNameAndObjMap = config.getGradeList().stream().filter(Objects::nonNull)
                .collect(Collectors.toMap(Grade::getGradeName, Function.identity(), (grade, grade2) -> grade));
        Map<String, Clazz> classNameAndObjMap = config.getClassList().stream().filter(Objects::nonNull)
                .collect(Collectors.toMap(Clazz::getClassName, Function.identity(), (clazz, clazz2) -> clazz));
        Map<String, Course> courseNameAndObjMap = config.getCourseList().stream().filter(Objects::nonNull)
                .collect(Collectors.toMap(Course::getCourseName, Function.identity(), (course, course2) -> course));
        Map<String, Teacher> teacherNameAndObjMap = config.getTeacherList().stream().filter(Objects::nonNull)
                .collect(Collectors.toMap(Teacher::getTeacherName, Function.identity(), (teacher, teacher2) -> teacher));
        // 解析年级课程数据
        gradeCourseDataList.stream().filter(Objects::nonNull)
                .forEach(gradeCourseData -> {
                    Grade grade = gradeNameAndObjMap.get(gradeCourseData.getGradeName());
                    Assert.notNull(grade);
                    gradeCourseData.setGradeId(grade.getId());
                    Course course = courseNameAndObjMap.get(gradeCourseData.getCourseName());
                    Assert.notNull(course);
                    gradeCourseData.setCourseId(course.getId());
                });
        // 解析班级课程数据
        Map<Long, Map<Long, GradeCourseData>> gradeIdAndCourseIdAndData = gradeCourseDataList.stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(GradeCourseData::getGradeId,
                        Collectors.toMap(GradeCourseData::getCourseId, Function.identity(),
                                (gradeCourseData, gradeCourseData2) -> gradeCourseData)));
        classCourseDataList.stream().filter(Objects::nonNull)
                .forEach(classCourseData -> {
                    Clazz clazz = classNameAndObjMap.get(classCourseData.getClassName());
                    Assert.notNull(clazz);
                    classCourseData.setClassId(clazz.getId());
                    classCourseData.setGradeId(clazz.getGradeId());
                    Course course = courseNameAndObjMap.get(classCourseData.getCourseName());
                    Assert.notNull(course);
                    classCourseData.setCourseId(course.getId());
                    Teacher teacher = teacherNameAndObjMap.get(classCourseData.getTeacherName());
                    Assert.notNull(teacher);
                    classCourseData.setTeacherSequence(teacher.getTeacherSequence());
                });

        List<Lesson> lessonList = Lists.newArrayList();
        classCourseDataList.stream().filter(Objects::nonNull)
                .forEach(classCourseData -> {
                    Map<Long, GradeCourseData> courseIdAndDataMap = gradeIdAndCourseIdAndData.get(classCourseData.getGradeId());
                    Assert.notNull(courseIdAndDataMap);
                    GradeCourseData gradeCourseData = courseIdAndDataMap.get(classCourseData.getCourseId());
                    IntStream.rangeClosed(1, gradeCourseData.getWeekDuration())
                            .forEach(value -> {
                                Lesson lesson = Lesson.newLesson(LessonTypeEnum.SINGLE_LESSON,
                                        courseNameAndObjMap.get(classCourseData.getCourseName()),
                                        teacherNameAndObjMap.get(classCourseData.getTeacherName()),
                                        classNameAndObjMap.get(classCourseData.getClassName()),
                                        1);
//                                singleLesson.setClassId(classCourseData.getClassId())
//                                        .setClassName(classCourseData.getClassName())
//                                        .setCourseId(classCourseData.getCourseId())
//                                        .setCourseName(classCourseData.getCourseName())
//                                        .setTeacherSequence(classCourseData.getTeacherSequence())
//                                        .setTeacherName(classCourseData.getTeacherName());
                                lessonList.add(lesson);
                            });
                });
        return lessonList;
    }

    private void print(Schedule schedule) {
        ScheduleContext config = schedule.getContext();
        int weekSchoolDayNum = config.getWeekSchoolDayNum();
        int dayLessonNum = config.getDayLessonNum();
        StringBuffer headBuffer = new StringBuffer();
        headBuffer.append(getCell("班级"));
        IntStream.rangeClosed(1, weekSchoolDayNum)
                .forEach(day -> {
                    IntStream.rangeClosed(1, dayLessonNum)
                            .forEach(section -> {
                                DayOfWeek dayOfWeek = DayOfWeek.of(day);
                                headBuffer.append(getCell(dayOfWeek.name() + "-" + section));
                            });
                });
        System.out.println(headBuffer);
        List<List<ScheduleSlot>> partitionList = Lists.partition(schedule.getGeneList(), weekSchoolDayNum * dayLessonNum);
        partitionList.stream().filter(Objects::nonNull)
                .forEach(scheduleSlotList -> {
                    StringBuffer lineBuffer = new StringBuffer();
                    // 理论上这里只有一个
                    List<String> roomNameList = scheduleSlotList.stream().filter(Objects::nonNull)
                            .map(ScheduleSlot::getRoom).filter(Objects::nonNull)
                            .map(Room::getRoomName)
                            .distinct()
                            .collect(Collectors.toList());
                    lineBuffer.append(getCell(StringUtils.join(roomNameList)));
                    scheduleSlotList.stream().forEach(slot -> {
                        if (Objects.nonNull(slot.getLesson())) {
                            Lesson lesson = slot.getLesson();
                            String courseName = Objects.nonNull(lesson.getCourse()) ?
                                    lesson.getCourse().getCourseName() : StringUtils.EMPTY;
                            List<String> teacherNameList = Optional.ofNullable(lesson.getTeacherList())
                                    .orElse(Collections.emptyList())
                                    .stream().filter(Objects::nonNull)
                                    .map(Teacher::getTeacherName)
                                    .collect(Collectors.toList());
                            lineBuffer.append(getCell("" + courseName + "-" + StringUtils.join(teacherNameList, ",")));
                        } else {
                            lineBuffer.append(getCell(StringUtils.EMPTY));
                        }
                    });
                    System.out.println(lineBuffer);
                });
    }

    @SneakyThrows
    private String getCell(String str) {
        int width = 15;
//        // gb2312 一个中文两个字节
//        // 一个中文打印时，相当于1.5个英文字符
//        int byteLength = str.getBytes("gb2312").length;
//        int strLength = str.length();
//        int chineseCharacterSize = byteLength - strLength;
//        int expectedWidth = (int) Math.ceil(width - chineseCharacterSize * 0.5) - 1;
//        String s = StringUtils.rightPad(str, expectedWidth);
//        return String.format("%s|", s);
        String s = StringUtils.rightPad(str, width);
        return String.format("%s\t|", s);
    }

    @Test
    public void ccc() {
        int i = countChinese("我爱我的祖国");
        System.out.println(i);
        System.out.println(countChinese("aaaaaa"));
        System.out.println(countChinese("数学(联合)-李萱萱"));
    }


    public static int countChinese(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        for (char c : chars) {
            if (isChinese(c)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    @SneakyThrows
    public static String getCell2(String str) {
        int width = 15;
//        String s = StringUtils.rightPad(str, width);
//        return String.format("%s\t|", s);
        String s = Optional.ofNullable(str).orElse(StringUtils.EMPTY);
        int tabSize = 4;
        int count = countChinese(s);
        int repeatSize = (int) Math.floor((width - (str.length() - count * 0.5)) / 4);
//        int repeatSize = (int) (tabSize - Math.floor(count / 2d) - Math.floor((str.length() - count) / 4d));
        s = s.concat(StringUtils.repeat("\t", repeatSize)).concat("|");
        return s;
    }

    @Test
    public void ppp() {
        StringBuffer head = new StringBuffer();
        head.append(getCell2("班级"));
        head.append(getCell2("MONDAY-1"));
        head.append(getCell2("TUESDAY-1"));
        StringBuffer line1 = new StringBuffer();
        line1.append(getCell2("[一年级1班]"));
        line1.append(getCell2("数学(连)-李萱萱"));
        line1.append(getCell2("政治(连)-杨瑜"));
        StringBuffer line2 = new StringBuffer();
        line2.append(getCell2("[一年级1班]"));
        line2.append(getCell2("政治(连)-曹涛"));
        line2.append(getCell2("政治-杨瑜"));
        System.out.println(head);
        System.out.println(line1);
        System.out.println(line2);

    }

}

