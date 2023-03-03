package com.yuge.ing.amqp;

import com.yuge.ing.amqp.message.ScheduleMessage;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class AmqpApplicationTests {

    @Resource
    private AmqpTemplate amqpTemplate;
    @Test
    void contextLoads() throws InterruptedException {
        ScheduleMessage scheduleMessage = new ScheduleMessage().setPlanId(111L);
        amqpTemplate.convertAndSend(AmqpConfig.SCHEDULE_EXCHANGE, AmqpConfig.SCHEDULE_ROUTING_KEY, scheduleMessage);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await(1, TimeUnit.MINUTES);
    }



}
