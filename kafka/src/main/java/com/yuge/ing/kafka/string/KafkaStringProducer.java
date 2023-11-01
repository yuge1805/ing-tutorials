package com.yuge.ing.kafka.string;

import com.yuge.ing.kafka.string.KafkaStringController.MsgDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: yuge
 * @date: 2023/10/11
 **/
@Component
public class KafkaStringProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaStringProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MsgDTO dto) {
        kafkaTemplate.send(dto.getTopic(), dto.getMessage());
    }

}
