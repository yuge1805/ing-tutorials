package com.yuge.ing.amqp.direct;

import com.yuge.ing.amqp.message.SmsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author: yuge
 * @date: 2023/3/9
 **/
@Slf4j
@Component
public class DirectProducer {
    protected static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

    /**
     * 消息数量
     */
    protected int messageNumber = 200;

    @Resource
    private AmqpTemplate amqpTemplate;

    @PostConstruct
    public void intMessage() {
//        scheduledExecutorService.schedule(() -> {
//            log.info("direct producer start.");
//            IntStream.range(0, messageNumber)
//                    .forEach(value -> {
//                        SmsMessage smsMessage = new SmsMessage()
//                                .setPhone(String.valueOf(value))
//                                .setContent(String.valueOf(value));
//                        log.info("produce [{}]", smsMessage);
//                        amqpTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_DEFAULT_ROUTING_KEY, smsMessage);
//                    });
//            log.info("direct producer end.");
//        }, 30, TimeUnit.SECONDS);
    }


}
