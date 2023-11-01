package com.yuge.ing.kafka.string;

import com.yuge.ing.kafka.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: yuge
 * @date: 2023/10/12
 **/
@Slf4j
@Component
public class KafkaStringConsumer {

//    @KafkaListener(id = "stringKafkaListener", topics = KafkaConfig.TOPIC_STRING)
    @KafkaListener(topics = KafkaConfig.TOPIC_STRING)
    public void listen(String in) {
      log.info("receive string msg: {}", in);
    }

}

