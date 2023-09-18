package com.yuge.ing.limiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author: yuge
 * @date: 2023/8/24
 **/
@Slf4j
public class RateLimterTest {

    @Test
    public void create() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(2d, 5l, TimeUnit.SECONDS);
        for (int i = 0; i < 10000; i++) {
//            double acquire = rateLimiter.acquire();
//            log.info("{}", acquire);
            boolean b = rateLimiter.tryAcquire();
            Thread.sleep(100);
            if (b) {
                log.info("{}", b);
            }
        }
    }

}
