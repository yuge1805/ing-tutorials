package com.yuge.ing.genetic.algorithm.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.DayOfWeek;
import java.util.List;

/**
 * @author: yuge
 * @date: 2022/12/30
 **/
@Data
@Accessors(chain = true)
public class ScheduleSlot implements Gene {

    /**
     * 教室
     */
    private Room room;

    /**
     * 周几； 例：周一
     */
    private DayOfWeek weekDay;

    /**
     * 第几天
     */
    private Integer day;

    /**
     * 第几节
     */
    private Integer section;

    /**
     * lesson
     */
    private Lesson lesson;

    /**
     * 是否禁用
     */
    private boolean disable;


}
