package com.yuge.ing.limiter;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 滑动窗口
 *
 * @author: yuge
 * @date: 2024/9/5
 **/
@Slf4j
public class SlidingWindowRateLimiter implements RateLimiter {

    private Lock lock = new ReentrantLock();

    /**
     * 窗口大小 毫秒
     */
    private long windowSizeMilliSecond = 10 * 1000;

    /**
     * 限制大小
     */
    private long limit = 10;

    private Deque<Long> requestTimestamps = new LinkedList<>();

    @Override
    public boolean isAllow() {
        lock.lock();
        try {
            long currentTime = System.currentTimeMillis();

            // 删除过期的请求
            while(!requestTimestamps.isEmpty() && (currentTime - requestTimestamps.peekFirst()) > windowSizeMilliSecond) {
                // 从队头删除过期的请求
                requestTimestamps.pollFirst();
            }

            if (requestTimestamps.size() < limit) {
                requestTimestamps.addLast(currentTime);
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
