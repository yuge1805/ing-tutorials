package com.yuge.ing.genetic.algorithm;

import com.yuge.ing.genetic.algorithm.context.ScheduleContext;
import com.yuge.ing.genetic.algorithm.model.Schedule;

/**
 * 排课
 *
 * @author: yuge
 * @date: 2023/1/6
 **/
public class CourseSchedulingGA extends GeneticAlgorithm<Schedule, ScheduleContext>{

    @Override
    Schedule newChromosome() {
        Schedule schedule = new Schedule(super.getContext());
        schedule.initGeneListFromContext();
        return schedule;
    }

    /**
     * 交叉
     *
     * @param t1
     * @param t2
     * @return
     */
    @Override
    Schedule crossover(Schedule t1, Schedule t2) {
        return t1.crossover(t2);
    }

    /**
     * 变异
     *
     * @param schedule
     * @return
     */
    @Override
    Schedule mutation(Schedule schedule) {
        return schedule.mutation();
    }

}
