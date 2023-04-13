package com.yuge.ing.genetic.algorithm;

import com.yuge.ing.genetic.algorithm.model.lesson.SingleLesson;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @author: yuge
 * @date: 2022/12/29
 **/
public class Tmp {

    @Test
    void ttt() {
        SecureRandom secureRandom = new SecureRandom();
        IntStream.range(0, 100).forEach(value -> {
//            System.out.println(secureRandom.nextDouble());
            System.out.println(secureRandom.nextInt(100));
        });
    }

    @Test
    void t() {
        SingleLesson a = new SingleLesson();
        a.setId(1L);
        a.setClassId(2L);

        SingleLesson b = new SingleLesson();
        b.setId(1L);
        b.setClassId(3L);

        System.out.println(a.equals(b));

        System.out.println(Objects.equals(a.hashCode(), b.hashCode()));
    }

    @Test
    void tt() {
        SecureRandom random = new SecureRandom();
        IntStream.range(0, 100).forEach(value -> {
            System.out.println(random.nextInt(1));
        });
    }


}
