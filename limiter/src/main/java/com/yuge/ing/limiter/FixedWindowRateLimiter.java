package com.yuge.ing.limiter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yuge
 * @date: 2024/9/5
 **/
@Slf4j
public class FixedWindowRateLimiter implements RateLimiter {

    private Lock lock = new ReentrantLock();

    /**
     * 窗口大小 毫秒
     */
    private long windowSizeMilliSecond = 10 * 1000;

    /**
     * 限制大小
     */
    private long limit = 10;

    /**
     * 当前窗口大小
     */
    private long currentWindowStartTime = System.currentTimeMillis();

    /**
     * 计数
     */
    private long count = 0;

    @Override
    public boolean isAllow() {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            if (now - currentWindowStartTime > windowSizeMilliSecond) {
                currentWindowStartTime = now;
                count = 0;
            }

            if (count < limit) {
                count++;
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("isAllow error!", e);
            return false;
        } finally {
            lock.unlock();
        }
    }

}
