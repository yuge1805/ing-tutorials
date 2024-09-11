package com.yuge.ing.limiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Guava RateLimiter基于令牌桶算法
 *
 * @author: yuge
 * @date: 2024/9/6
 **/
@Slf4j
public class GuavaRateLimiterTest {

    @Test
    void isAllow() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        com.google.common.util.concurrent.RateLimiter rateLimiter = RateLimiter.create(1.0);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            for (int i = 0; i < 100; i++) {
                fixedThreadPool.submit(() -> {
                    log.info("isAllow: {}", rateLimiter.tryAcquire());
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }, 3, 10, TimeUnit.SECONDS);

        countDownLatch.await(3, TimeUnit.MINUTES);
    }

}