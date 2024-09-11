package com.yuge.ing.limiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author: yuge
 * @date: 2024/9/5
 **/
public class GuavaRateLimiter {

    public static void main(String[] args) {

        RateLimiter rateLimiter = RateLimiter.create(5.0);

    }

}
