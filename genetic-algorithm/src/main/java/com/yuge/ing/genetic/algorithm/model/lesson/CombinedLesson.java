package com.yuge.ing.genetic.algorithm.model.lesson;

import com.yuge.ing.genetic.algorithm.model.Lesson;
import lombok.Data;

import java.util.List;

/**
 * 合堂课
 *
 * @author: yuge
 * @date: 2022/12/30
 **/
@Data
public class CombinedLesson extends Lesson {

    /**
     * 合堂课明细
     */
    private List<SingleLesson> lessonList;

//    public CombinedLesson(List<SingleLesson> lessonList) {
//        super(LessonTypeEnum.COMBINED_LESSON);
//        Long parentId = this.getId();
//        this.lessonList = lessonList;
//        lessonList.stream()
//                .filter(Objects::nonNull)
//                .forEach(lesson -> {
//                    lesson.setParentId(parentId);
//                    lesson.setType(LessonTypeEnum.COMBINED_LESSON);
//                });
//    }
}
