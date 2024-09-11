package com.yuge.ing.limiter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 漏桶算法
 *
 * @author: yuge
 * @date: 2024/9/6
 **/
@Slf4j
public class LeakyBucketRateLimiter implements RateLimiter {

    private Lock lock = new ReentrantLock();

    /**
     * 桶大小
     */
    private double capacity = 20;

    /**
     * 当前桶中水量
     */
    private double water = 0;

    /**
     * 单位时间通过数量
     */
    private double allowNum = 10;

    /**
     * 单位时间
     */
    private long timeUnitMilliSecond = 10 * 1000;

    /**
     * 速率
     */
    private double rate = allowNum / timeUnitMilliSecond;

    /**
     * 上次流出时间
     */
    private long lastLeakTime = System.currentTimeMillis();

    @Override
    public boolean isAllow() {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            double leaks = (now - lastLeakTime) * rate;
            if (leaks > 0) {
                // 更新桶中的剩余水量
                water = Math.max(0, water - leaks);
                // 更新最后一次漏水时间
                lastLeakTime = now;
            }

            if (water < capacity) {
                water++;
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
