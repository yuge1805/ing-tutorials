package com.yuge.ing.genetic.algorithm.model;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yuge.ing.genetic.algorithm.context.ScheduleContext;
import com.yuge.ing.genetic.algorithm.rule.PreScheduleRule;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 课表
 *
 * @author: yuge
 * @date: 2022/12/30
 **/
@Data
public class Schedule extends AbstractChromosome<Schedule, ScheduleSlot, ScheduleContext> {

    public static final int DEFAULT_START_INCLUSIVE = 1;

    private SecureRandom random;

    /**
     * 课集合（包含连堂课、合堂课）
     */
    private List<Lesson> lessonList;

    /**
     * 班级-课程集合
     */
    private Map<Long, List<Lesson>> classIdAndLessonsMap;

    /**
     * 教室id集合
     */
    private List<Long> roomIdList;

    public Schedule(ScheduleContext context) {
        super(context);
        context.check();
        random = new SecureRandom();
    }

    /**
     * 通过上下文初始化基因集合
     *
     * @return
     */
    @Override
    public Schedule initGeneListFromContext() {
        this.parseContext(context);
        this.initSlot(context);
        this.initRule(context);
        this.assignLesson();
        return this;
    }

    /**
     * assign
     */
    private void assignLesson() {
        IntStream.rangeClosed(DEFAULT_START_INCLUSIVE, context.getWeekSchoolDayNum())
                .forEach(day -> {
                    DayOfWeek weekDay = DayOfWeek.of(day);
                    IntStream.rangeClosed(DEFAULT_START_INCLUSIVE, context.getDayLessonNum())
                            .forEach(section -> {
                                this.assignLessonToDaySection(weekDay, section);
                            });
                });
    }

    private void assignLessonToDaySection(DayOfWeek weekDay, int section) {
        List<ScheduleSlot> currentSlotList = getDaySectionSlots(weekDay, section);
        currentSlotList.stream()
                .filter(Objects::nonNull)
                .filter(scheduleSlot -> Objects.isNull(scheduleSlot.getLesson()))
                .filter(scheduleSlot -> Objects.nonNull(scheduleSlot.getRoom())
                        && Objects.nonNull(scheduleSlot.getRoom().getRoomDefaultClassId()))
                .forEach(scheduleSlot -> {
                    Long roomDefaultClassId = scheduleSlot.getRoom().getRoomDefaultClassId();
                    Optional<Lesson> lessonOptional = getClassLesson(roomDefaultClassId, weekDay, section);
                    if (lessonOptional.isPresent()) {
                        scheduleSlot.setLesson(lessonOptional.get());
                    }
                });
        // todo 教室无默认班级id的处理
    }

    /**
     * 获取某天某节的所有槽位
     *
     * @param weekDay
     * @param section
     * @return
     */
    private List<ScheduleSlot> getDaySectionSlots(DayOfWeek weekDay, int section) {
        List<ScheduleSlot> currentSlotList = roomIdList.stream().filter(Objects::nonNull)
                .map(roomId -> getSlot(roomId, weekDay, section))
                .collect(Collectors.toList());
        return currentSlotList;
    }

    private Optional<Lesson> getClassLesson(Long classId, DayOfWeek weekDay, int section) {
        List<Lesson> lessonList = classIdAndLessonsMap.getOrDefault(classId, Collections.emptyList());
        if (CollectionUtils.isEmpty(lessonList)) {
            return Optional.empty();
        }
        List<ScheduleSlot> slotList = getDaySectionSlots(weekDay, section);
        // 随机排课
        List<Integer> tryIndexList = Lists.newArrayList();
        do {
            int randomIndex = random.nextInt(lessonList.size());
            if (tryIndexList.contains(randomIndex)) {
                continue;
            }
            tryIndexList.add(randomIndex);
            Lesson lesson = lessonList.get(randomIndex);
            boolean isMatch = isMatch(lesson, slotList, weekDay, section);
            if (!isMatch) {
                lessonList.remove(lesson);
                return Optional.of(lesson);
            }
        } while (tryIndexList.size() < lessonList.size() / 2);
        // 当随机过多时，仍不满足排课条件时，挨个遍历排课
        List<Lesson> untriedLessonList = IntStream.range(0, lessonList.size())
                .filter(value -> !tryIndexList.contains(value))
                .boxed()
                .map(i -> lessonList.get(i))
                .collect(Collectors.toList());
        for (Lesson lesson : untriedLessonList) {
            boolean isMatch = isMatch(lesson, slotList, weekDay, section);
            if (!isMatch) {
                lessonList.remove(lesson);
                return Optional.of(lesson);
            }
        }
        return Optional.empty();
    }

    /**
     * 是否匹配
     *
     * @param lesson
     * @param slotList
     * @param weekDay
     * @param section
     * @return
     */
    private boolean isMatch(Lesson lesson, List<ScheduleSlot> slotList, DayOfWeek weekDay, int section) {
        // todo 扩展其余规则校验
        boolean teacherOverlap = teacherOverlap(lesson, slotList);
        return teacherOverlap;
    }

    /**
     * 是否存在老师重叠
     *
     * @param lesson
     * @param slotList
     * @return
     */
    private boolean teacherOverlap(Lesson lesson, List<ScheduleSlot> slotList) {
        if (CollectionUtils.isEmpty(slotList)) {
            return false;
        }
        List<Lesson> lessons = slotList.stream()
                .filter(Objects::nonNull)
                .filter(scheduleSlot -> Objects.nonNull(scheduleSlot.getLesson()))
                .map(ScheduleSlot::getLesson)
                .collect(Collectors.toList());
        return lesson.teacherOverlap(lessons);
    }

    /**
     * parse context
     *
     * @param context
     */
    private void parseContext(ScheduleContext context) {
        lessonList = context.getLessonList();

        int expectedSize = context.getWeekSchoolDayNum()
                * context.getDayLessonNum()
                * CollectionUtils.size(context.getClassList());
        classIdAndLessonsMap = Maps.newHashMapWithExpectedSize(expectedSize);
        for (Lesson lesson : lessonList) {
            if (Objects.isNull(lesson)) {
                continue;
            }
            if (CollectionUtils.isEmpty(lesson.getClassList())) {
                continue;
            }
            lesson.getClassList()
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(clazz -> {
                        List<Lesson> classLessionList = classIdAndLessonsMap.get(clazz.getId());
                        if (Objects.isNull(classLessionList)) {
                            classLessionList = Lists.newArrayListWithExpectedSize(context.getWeekSchoolDayNum() * context.getDayLessonNum());
                            classIdAndLessonsMap.put(clazz.getId(), classLessionList);
                        }
                        classLessionList.add(lesson);
                    });
        }

        roomIdList = context.getRoomList()
                .stream()
                .filter(Objects::nonNull)
                .map(Room::getId)
                .collect(Collectors.toList());
    }

    /**
     * 初始化规则
     *
     * @param context
     */
    private void initRule(ScheduleContext context) {
        List<PreScheduleRule> preScheduleRuleList = context.getRuleList()
                .stream()
                .filter(Objects::nonNull)
                .filter(rule -> rule instanceof PreScheduleRule)
                .map(rule -> (PreScheduleRule) rule)
                .collect(Collectors.toList());
        for (PreScheduleRule rule : preScheduleRuleList) {
            Optional<Lesson> lessonOptional = this.getAndRemoveLesson(rule.getClassId(), rule.getCourseId(), rule.getTeacherSequence());
            if (!lessonOptional.isPresent()) {
                continue;
            }
            ScheduleSlot slot = getSlot(getClassDefaultRoomId(rule.getClassId()), rule.getWeekDay(), rule.getSection());
            // TODO 设置并校验
            slot.setLesson(lessonOptional.get());
        }
    }

    /**
     * 获取班级的默认教室Id
     *
     * @param classId
     * @return
     */
    private Long getClassDefaultRoomId(Long classId) {
        // TODO
        // 当前不存在教室基础信息，现定义班级id即教室id
        return classId;
    }

    private ScheduleSlot getSlot(Long roomId, DayOfWeek weekDay, Integer section) {
        Assert.isTrue(weekDay.getValue() <= context.getWeekSchoolDayNum(),
                () -> new IllegalArgumentException(String.format("invalid weekDay [%s], over the config weekSchoolDayNum limit!", weekDay)));
        Assert.isTrue(section <= context.getDayLessonNum(),
                () -> new IllegalArgumentException(String.format("invalid section [%s], over the config dayLessonNum limit!", section)));
        int roomIndex = roomIdList.indexOf(roomId);
        Assert.isTrue(roomIndex >= 0,
                () -> new IllegalArgumentException(String.format("invalid roomId [%s], not in roomIdList!", roomId)));
        int slotIndex = roomIndex * context.getWeekSchoolDayNum() * context.getDayLessonNum()
                + (weekDay.getValue() - 1) * context.getDayLessonNum() + (section - 1);
        return geneList.get(slotIndex);
    }

    /**
     * 初始化槽
     *
     * @param context
     */
    private void initSlot(ScheduleContext context) {
        int slotSize = context.getWeekSchoolDayNum() * context.getDayLessonNum() * context.getRoomList().size();
        geneList = new ArrayList<>(slotSize);
        context.getRoomList()
                .stream()
                .filter(Objects::nonNull)
                .forEach(room -> {
                    geneList.addAll(getRoomWeekSlot(room, context));
                });
    }

    private List<ScheduleSlot> getRoomWeekSlot(Room room, ScheduleContext context) {
        List<ScheduleSlot> resultList = Lists.newArrayList();
        IntStream.rangeClosed(DEFAULT_START_INCLUSIVE, context.getWeekSchoolDayNum())
                .forEach(value -> {
                    resultList.addAll(getRoomDaySlot(room, DayOfWeek.of(value), context.getDayLessonNum()));
                });
        return resultList;
    }

    private List<ScheduleSlot> getRoomDaySlot(Room room, DayOfWeek weekDay, Integer dayLessonNum) {
        List<ScheduleSlot> resultList = Lists.newArrayList();
        IntStream.rangeClosed(DEFAULT_START_INCLUSIVE, dayLessonNum)
                .forEach(value -> {
                    ScheduleSlot slot = new ScheduleSlot();
                    slot.setRoom(room)
                            .setWeekDay(weekDay)
                            .setDay(weekDay.getValue())
                            .setSection(value);
                    resultList.add(slot);
                });
        return resultList;
    }

    /**
     * 获取基因集合
     *
     * @return
     */
    @Override
    public List<ScheduleSlot> getGeneList() {
        return geneList;
    }

    public Optional<Lesson> getLesson(Long classId, Long courseId, String teacherSequence) {
        return classIdAndLessonsMap.getOrDefault(classId, Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(lesson -> lesson.courseOverlap(courseId)
                                && lesson.teacherOverlap(teacherSequence)
                )
                .findFirst();
    }

    public Optional<Lesson> getAndRemoveLesson(Long classId, Long courseId, String teacherSequence) {
        List<Lesson> lessonList = classIdAndLessonsMap.getOrDefault(classId, Collections.emptyList());
        if (CollectionUtils.isEmpty(lessonList)) {
            return Optional.empty();
        }
        Optional<Lesson> optional = getLesson(classId, courseId, teacherSequence);
        if (optional.isPresent()) {
            lessonList.remove(optional.get());
        }
        return optional;
    }

    /**
     * 交叉
     *
     * @param schedule
     * @return 新课表
     */
    @Override
    public Schedule crossover(Schedule schedule) {
        // todo crossover
        return null;
    }

    /**
     * 变异
     *
     * @return 新课表
     */
    @Override
    public Schedule mutation() {
        // todo mutation
        return null;
    }

}
