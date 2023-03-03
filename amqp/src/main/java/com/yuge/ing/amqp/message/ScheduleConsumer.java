package com.yuge.ing.amqp.message;

import com.rabbitmq.client.Channel;
import com.yuge.ing.amqp.AmqpConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author: yuge
 * @date: 2023/2/23
 **/
@Slf4j
@Component
public class ScheduleConsumer {

    @SneakyThrows
    @RabbitListener(queues = AmqpConfig.SCHEDULE_QUEUE)
    public void listen(ScheduleMessage scheduleMessage, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        log.info(scheduleMessage.toString());
        channel.basicAck(deliveryTag, false);
    }

}
