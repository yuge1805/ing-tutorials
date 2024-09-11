package com.yuge.ing.limiter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yuge
 * @date: 2024/9/11
 **/
@Slf4j
public class TokenBucketRateLimiter implements RateLimiter {

    private Lock lock = new ReentrantLock();

    /**
     * 桶大小
     */
    private double capacity = 20;

    /**
     * 当前桶中令牌数量
     */
    private double remainTokenNum = 0;

    /**
     * 单位时间生成令牌数量
     */
    private double tokenNumPerTimeUnit = 10;

    /**
     * 单位时间
     */
    private long timeUnitMilliSecond = 10 * 1000;

    /**
     * 令牌生成速率
     */
    private double tokenRate = tokenNumPerTimeUnit / timeUnitMilliSecond;

    /**
     * 上次令牌生成时间
     */
    private long lastGenerateTime = System.currentTimeMillis();

    @Override
    public boolean isAllow() {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            double newTokenNum = (now - lastGenerateTime) * tokenRate;
            if (newTokenNum > 0) {
                remainTokenNum = Math.min(capacity, remainTokenNum + newTokenNum);
                lastGenerateTime = now;
            }

            if (remainTokenNum > 1) {
                remainTokenNum--;
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
