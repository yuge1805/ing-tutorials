package com.yuge.ing.aviator.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangbw
 * @since 2025/7/29
 */
@Component
public class ExpressionCache {

    /**
     * cache
     */
    private Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(10 * 1000)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .recordStats()
            .build();

    /**
     * get cache
     *
     * @param expression
     * @return
     */
    public String get(String expression) {
        try {
            // 通用替换规则：${任意类型:变量名} -> 任意类型__变量名
            return cache.get(expression, () -> expression.replaceAll("\\$\\{([^:{}]+:[^}]+)}", "$1")
                    .replaceAll(":", "__"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get expression cache:" + expression, e);
        }
    }

}
