package com.yuge.ing.amqp.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: yuge
 * @date: 2023/4/13
 **/
@Slf4j
@Component
public class MqReturnCallback implements RabbitTemplate.ReturnsCallback {

    @Resource
    private MessageConverter messageConverter;

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("return callback, {}", returned);
        Object msgObject = messageConverter.fromMessage(returned.getMessage());
        log.info(msgObject.toString());
    }

}
