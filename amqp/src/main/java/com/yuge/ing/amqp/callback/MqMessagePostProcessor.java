package com.yuge.ing.amqp.callback;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;

import java.util.Objects;

/**
 * 执行顺序
 * 1、MessagePostProcessor
 * 2、CorrelationDataPostProcessor
 *
 * @author: yuge
 * @date: 2023/4/21
 **/
public interface MqMessagePostProcessor extends MessagePostProcessor {

    String getCorrelationId();

    default Message postProcessMessage(Message message) throws AmqpException {
        if (Objects.isNull(message)) {
            return message;
        }
        MessageProperties messageProperties = message.getMessageProperties();
        if (Objects.isNull(messageProperties)) {
            messageProperties = new MessageProperties();
        }
        messageProperties.setCorrelationId(getCorrelationId());
        return message;
    }


    default Message postProcessMessage(Message message, Correlation correlation) {
        if (Objects.isNull(message)) {
            return message;
        }
        if (Objects.nonNull(correlation)
                && correlation instanceof CorrelationData) {
            MessageProperties messageProperties = message.getMessageProperties();
            if (Objects.isNull(messageProperties)) {
                messageProperties = new MessageProperties();
            }
            messageProperties.setCorrelationId(((CorrelationData) correlation).getId());
            return message;
        }
        return postProcessMessage(message);
    }

}
