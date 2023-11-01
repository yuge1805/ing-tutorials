package com.yuge.ing.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author: yuge
 * @date: 2023/10/12
 **/
@Configuration
public class KafkaConfig {

    public final static String TOPIC_OBJECT = "topic_object";

    public final static String TOPIC_STRING = "topic_string";

    @Bean
    public NewTopic topicObject() {
        return TopicBuilder.name(TOPIC_OBJECT)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicString() {
        return TopicBuilder.name(TOPIC_STRING)
                .partitions(3)
                .replicas(1)
                .build();
    }



}
