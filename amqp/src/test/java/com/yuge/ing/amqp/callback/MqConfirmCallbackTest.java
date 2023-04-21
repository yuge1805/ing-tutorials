package com.yuge.ing.amqp.callback;

import com.yuge.ing.amqp.direct.DirectConfig;
import com.yuge.ing.amqp.message.SmsMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MqConfirmCallbackTest {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private MessageConverter messageConverter;


    /**
     * send
     * RabbitTemplate使用CorrelationDataPostProcessor
     * 发送完之后，获取CorrelationId路径：Message#MessageProperties#CorrelationId
     *
     * 局限性：发送到MQ之后，才能获取到CorrelationId； 如果消息需要保存到DB，需要考虑提交事务、发送到MQ的顺序；
     * 建议提交事务后，再发送到MQ；此时需要预先知道CorrelationId；
     */
    @Test
    @SneakyThrows
    public void send() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SmsMessage smsMessage = new SmsMessage("1-send", "send");
        Message message = messageConverter.toMessage(smsMessage, new MessageProperties());
        amqpTemplate.send(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_DEFAULT_ROUTING_KEY, message);
        log.info("send correlationId: [{}]", message.getMessageProperties().getCorrelationId());
        countDownLatch.await(3, TimeUnit.MINUTES);
    }

    @Test
    @SneakyThrows
    public void convertAndSend() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SmsMessage smsMessage = new SmsMessage("1-convertAndSend", "convertAndSend");
        String correlationId = UUID.randomUUID().toString();
        log.info("correlationId: {}", correlationId);
//        MqMessagePostProcessor mqMessagePostProcessor = () -> correlationId;
        amqpTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_DEFAULT_ROUTING_KEY,
                smsMessage, (MqMessagePostProcessor) () -> correlationId);
        countDownLatch.await(3, TimeUnit.MINUTES);
    }

    @Test
    @SneakyThrows
    public void sendMsgToNonexistentExchange() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SmsMessage smsMessage = new SmsMessage("1-sendMsgToNonexistentExchange", "sendMsgToNonexistentExchange");
        Message message = messageConverter.toMessage(smsMessage, new MessageProperties());
        amqpTemplate.send(DirectConfig.DIRECT_EXCHANGE.concat("Nonexistent"), DirectConfig.DIRECT_DEFAULT_ROUTING_KEY, message);
        log.info("send correlationId: [{}]", message.getMessageProperties().getCorrelationId());
        countDownLatch.await(3, TimeUnit.MINUTES);
    }

    @Test
    @SneakyThrows
    public void sendMsgToNonexistentRoutingKey() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SmsMessage smsMessage = new SmsMessage("2", "sendMsgToNonexistentRoutingKey");
        Message message = messageConverter.toMessage(smsMessage, new MessageProperties());
        amqpTemplate.send(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_DEFAULT_ROUTING_KEY.concat("Nonexistent"), message);
        log.info("send correlationId: [{}]", message.getMessageProperties().getCorrelationId());
        countDownLatch.await(3, TimeUnit.MINUTES);
    }

}