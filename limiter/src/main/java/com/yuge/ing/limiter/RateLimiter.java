package com.yuge.ing.limiter;

/**
 * @author: yuge
 * @date: 2024/9/5
 **/
public interface RateLimiter {

    /**
     * isAllow
     *
     * @return
     */
    boolean isAllow();

}
