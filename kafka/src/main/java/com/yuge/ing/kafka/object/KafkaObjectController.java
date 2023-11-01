package com.yuge.ing.kafka.object;

import com.yuge.ing.kafka.model.IngCustomMessage;
import com.yuge.ing.kafka.string.KafkaStringProducer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.yuge.ing.kafka.config.KafkaConfig.TOPIC_OBJECT;

/**
 * @author: yuge
 * @date: 2023/10/11
 **/
@Slf4j
@RestController
@RequestMapping("/object/produce")
public class KafkaObjectController {

    @Resource
    private KafkaTemplate<String, IngCustomMessage> kafkaTemplate;

    @PostMapping
    public String send(@RequestBody MsgDTO dto) {
        String message = Optional.ofNullable(dto.getMessage()).orElse("");
        message = message.concat(new Date().toString());
        String topic = Optional.ofNullable(dto.getTopic()).orElse(TOPIC_OBJECT);
        kafkaTemplate.send(topic, new IngCustomMessage("xxx", message));
        return "success";
    }

    @PostMapping("/sync")
    public String sendSync(@RequestBody MsgDTO dto) {
        String message = Optional.ofNullable(dto.getMessage()).orElse("");
        message = message.concat(new Date().toString());
        String topic = Optional.ofNullable(dto.getTopic()).orElse(TOPIC_OBJECT);
        IngCustomMessage ingCustomMessage = new IngCustomMessage("xxx", message);
        // blocking
        try {
            ListenableFuture<SendResult<String, IngCustomMessage>> listenableFuture = kafkaTemplate.send(topic, ingCustomMessage);
            // 降低超时时间，模拟超时
//            SendResult<String, IngCustomMessage> sendResult = listenableFuture.get(1, TimeUnit.MILLISECONDS);
            SendResult<String, IngCustomMessage> sendResult = listenableFuture.get(10, TimeUnit.SECONDS);
            log.info("sync send result: [{}]", sendResult);
        } catch (ExecutionException e) {
            log.info(String.format("send error! message: [%s]", ingCustomMessage), e);
        } catch (TimeoutException | InterruptedException e) {
            log.info(String.format("send error! message: [%s]", ingCustomMessage), e);
        }
        return "success";
    }

    @PostMapping("/async")
    public String sendAsync(@RequestBody MsgDTO dto) {
        String message = Optional.ofNullable(dto.getMessage()).orElse("");
        message = message.concat(new Date().toString());
        String topic = Optional.ofNullable(dto.getTopic()).orElse(TOPIC_OBJECT);
        IngCustomMessage ingCustomMessage = new IngCustomMessage("xxx", message);
        // Non blocking
        try {
            ListenableFuture<SendResult<String, IngCustomMessage>> listenableFuture = kafkaTemplate.send(topic, ingCustomMessage);
            listenableFuture.addCallback(new KafkaSendCallback<String, IngCustomMessage>() {
                @Override
                public void onSuccess(SendResult<String, IngCustomMessage> result) {
                    log.info("async send success, result: [{}]", result);
                }
                @Override
                public void onFailure(KafkaProducerException ex) {
                    ProducerRecord<Object, Object> failedProducerRecord = ex.getFailedProducerRecord();
                    log.info("async send fail, failedProducerRecord: [{}]", failedProducerRecord);
                    log.info(String.format("async send failure! message: [%s]", ingCustomMessage), ex);
                }
            });
            log.info("async send end...");
        } catch (Exception e) {
            log.info(String.format("send error! message: [%s]", ingCustomMessage), e);
        }
        return "success";
    }


    @Data
    public static class MsgDTO {

        private String topic;

        private String message;

    }

}
