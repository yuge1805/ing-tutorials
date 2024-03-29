package com.yuge.ing.concurrent.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.logging.SimpleFormatter;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

/**
 * @author: yuge
 * @date: 2024/3/28
 **/
@Component
@RequiredArgsConstructor
public class OrderNumGenerator {

//    private final static String lock = "lock:order-num-generator";
    private final RedisTemplate redisTemplate;

    private final String ORDER_SEQ_KEY = "order:seq:";

    public String getOrderNum() {
        String date = LocalDate.now().format(BASIC_ISO_DATE);
        String key = ORDER_SEQ_KEY.concat(date);
        Long increment = redisTemplate.opsForValue().increment(key);
        return increment.toString();
    }

}
