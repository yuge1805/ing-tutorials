package com.yuge.ing.kafka.string;

import com.yuge.ing.kafka.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author: yuge
 * @date: 2023/10/12
 **/
@Slf4j
@Component
public class KafkaStringConsumer {

//    @KafkaListener(id = "stringKafkaListener", topics = KafkaConfig.TOPIC_STRING)

    /**
     *
     * concurrency: 并发数； 会启动concurrency线程数量消费，应小于等于topic的partition数量
     *
     * @param in
     * @param ack
     */
    @KafkaListener(topics = KafkaConfig.TOPIC_STRING, concurrency = "2", clientIdPrefix = "stringConsumer")
    public void listen(String in, Acknowledgment ack) {
      log.info("receive string msg: {}", in);
        try {
            ack.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

