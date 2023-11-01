package com.yuge.ing.kafka.object;

import com.yuge.ing.kafka.config.KafkaConfig;
import com.yuge.ing.kafka.model.IngCustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: yuge
 * @date: 2023/10/12
 **/
@Slf4j
@Component
public class KafkaObjectConsumer {

//    @KafkaListener(id = "objectKafkaListener", topics = KafkaConfig.TOPIC_OBJECT)
    @KafkaListener(topics = KafkaConfig.TOPIC_OBJECT)
    public void listen(IngCustomMessage icm) {
      log.info("receive object msg: {}", icm);
    }

}
