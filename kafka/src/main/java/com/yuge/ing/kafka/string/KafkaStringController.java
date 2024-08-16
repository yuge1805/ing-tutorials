package com.yuge.ing.kafka.string;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

import static com.yuge.ing.kafka.config.KafkaConfig.TOPIC_OBJECT;
import static com.yuge.ing.kafka.config.KafkaConfig.TOPIC_STRING;

/**
 * @author: yuge
 * @date: 2023/10/11
 **/
@RestController
@RequestMapping("/string/produce")
public class KafkaStringController {

    @Resource
    private KafkaStringProducer kafkaStringProducer;

    @PostMapping
    public String send(@RequestBody MsgDTO dto) {
        for (int i = 0; i < 10; i++) {
            String msg = dto.getKey() + "-" + i + dto.getMessage() + new Date().toString();
            kafkaStringProducer.send(TOPIC_STRING, dto.getKey(), msg);
        }
        return "success";
    }


    @Data
    public static class MsgDTO {

        private String topic;

        private String key;

        private String message;

    }

}
