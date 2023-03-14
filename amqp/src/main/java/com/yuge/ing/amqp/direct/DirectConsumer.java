package com.yuge.ing.amqp.direct;

import com.rabbitmq.client.Channel;
import com.yuge.ing.amqp.message.SmsMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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
public class DirectConsumer {

    /**
     *  concurrency 1-1，同时消费1，最大同时消费1
     *
     *  踩坑：
     *  @RabbitListener(queues = DirectConfig.DIRECT_QUEUE, concurrency = "1")
     *  spring.rabbitmq.listener.simple.concurrency: 5
     *  spring.rabbitmq.listener.simple.max-concurrency: 20
     *  此时：监听者容器concurrency为1，max-concurrency为20
     *
     */
//    @SneakyThrows
//    @RabbitListener(queues = DirectConfig.DIRECT_QUEUE, concurrency = "1-1")
//    public void listen(SmsMessage smsMessage, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
//        log.info("direct consumer start: {}", smsMessage.getPhone());
//        Thread.sleep(6000);
//        channel.basicAck(deliveryTag, false);
//        log.info("direct consumer end: {}", smsMessage.getPhone());
//    }


    @SneakyThrows
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = DirectConfig.DIRECT_QUEUE, durable = "true"),
            exchange = @Exchange(name = DirectConfig.DIRECT_EXCHANGE, type = ExchangeTypes.DIRECT),
            key = DirectConfig.DIRECT_DEFAULT_ROUTING_KEY), concurrency = "1-1")
    public void listen2(SmsMessage smsMessage, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        log.info("direct consumer start: {}", smsMessage.getPhone());
        Thread.sleep(6000);
        channel.basicAck(deliveryTag, false);
        log.info("direct consumer end: {}", smsMessage.getPhone());
    }


}
