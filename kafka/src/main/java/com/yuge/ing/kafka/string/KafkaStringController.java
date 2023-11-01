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
        String message = Optional.ofNullable(dto.getMessage()).orElse("");
        dto.setMessage(message.concat(new Date().toString()));
        dto.setTopic(Optional.ofNullable(dto.getTopic()).orElse(TOPIC_STRING));
        kafkaStringProducer.send(dto);
        return "success";
    }


    @Data
    public static class MsgDTO {

        private String topic;

        private String message;

    }

}
