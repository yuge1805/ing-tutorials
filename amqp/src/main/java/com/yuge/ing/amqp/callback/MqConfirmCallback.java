package com.yuge.ing.amqp.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: yuge
 * @date: 2023/4/13
 **/
@Slf4j
@Component
public class MqConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("confirm callback, correlationDataId: {}, ack: {}, cause: {}, message: {}",
                correlationData.getId(), ack, cause, correlationData.getReturned());
    }

}
