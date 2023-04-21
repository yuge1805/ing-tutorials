package com.yuge.ing.amqp.callback;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.CorrelationDataPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

/**
 * @author: yuge
 * @date: 2023/4/14
 **/
@Slf4j
@Component
public class MpCorrelationDataPostProcessor implements CorrelationDataPostProcessor {

    @Override
    public CorrelationData postProcess(Message message, CorrelationData correlationData) {
        if (Objects.nonNull(correlationData)) {
            log.debug("exist correlationData: [{}]", correlationData);
            return correlationData;
        }
        if (Objects.isNull(message)
                || Objects.isNull(message.getMessageProperties())
                || StringUtils.isBlank(message.getMessageProperties().getCorrelationId())) {
            String correlationId = UUID.randomUUID().toString();
            message.getMessageProperties().setCorrelationId(correlationId);
            // todo 绑定消息、correlationId关系
            return new CorrelationData(correlationId);
        }
        String correlationId = message.getMessageProperties().getCorrelationId();
        log.info("message properties has correlationId [{}]", correlationId);
        return new CorrelationData(correlationId);
    }

}
