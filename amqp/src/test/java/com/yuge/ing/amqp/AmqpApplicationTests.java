package com.yuge.ing.amqp;

import com.yuge.ing.amqp.direct.DirectConfig;
import com.yuge.ing.amqp.message.SmsMessage;
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
        SmsMessage smsMessage = new SmsMessage().setPhone("11111");
        amqpTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_DEFAULT_ROUTING_KEY, smsMessage);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await(1, TimeUnit.MINUTES);
    }



}
