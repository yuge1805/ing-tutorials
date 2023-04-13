package com.yuge.ing.genetic.algorithm;

import com.google.common.collect.Lists;
import com.yuge.ing.genetic.algorithm.context.ScheduleContext;
import com.yuge.ing.genetic.algorithm.model.Clazz;
import com.yuge.ing.genetic.algorithm.model.Lesson;
import com.yuge.ing.genetic.algorithm.model.Schedule;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GeneticAlgorithmTest {

    @Test
    void selectPopulation() {
        ScheduleContext context = new ScheduleContext();
        context.setWeekSchoolDayNum(7);
        context.setDayLessonNum(5);
        context.setClassList(Lists.newArrayList(new Clazz()));
        context.setLessonList(Lists.newArrayList(new Lesson()));
        CourseSchedulingGA ga = new CourseSchedulingGA();
        SecureRandom random = new SecureRandom();
        List<Schedule> scheduleList = IntStream.range(0, ga.getPopulationSize())
                .boxed()
                .map(value -> {
                    Schedule schedule = new Schedule(context);
                    schedule.setFitness(random.nextInt(100));
                    return schedule;
                })
                .collect(Collectors.toList());
        ga.setPopulations(scheduleList);

        List<Schedule> selectList = ga.selectPopulation();
        System.out.println(selectList);
    }

}