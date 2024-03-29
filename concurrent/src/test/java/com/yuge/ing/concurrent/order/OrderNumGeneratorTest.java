package com.yuge.ing.concurrent.order;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderNumGeneratorTest {

    @Resource
    private OrderNumGenerator orderNumGenerator;

    @Test
    public void test() {
        String orderNum = orderNumGenerator.getOrderNum();
        System.out.println(orderNum);
    }

    @Test
    public void testByThread() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024));
        List<String> resultList = Collections.synchronizedList(new ArrayList<>());
        for (int i = 1; i <= 1000; i++) {
            executor.submit(() -> {
                String orderNum = orderNumGenerator.getOrderNum();
                System.out.println("num:" + orderNum);
                resultList.add(orderNum);
            });
        }
        Thread.sleep(10 * 1000);
        System.out.println("total : " + resultList.size());
        List<String> collect = resultList.stream().distinct().collect(Collectors.toList());
        System.out.println("total : " + collect.size());
        countDownLatch.await(60, TimeUnit.SECONDS);
    }

}