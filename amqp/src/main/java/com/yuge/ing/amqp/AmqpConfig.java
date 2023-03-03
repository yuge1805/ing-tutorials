package com.yuge.ing.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: yuge
 * @date: 2023/2/23
 **/
@Configuration
public class AmqpConfig {

    public static final String SCHEDULE_QUEUE = "curriculum.schedule.queue";

    public static final String SCHEDULE_EXCHANGE = "curriculum.schedule.exchange";

    public static final String SCHEDULE_ROUTING_KEY = "schedule";

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue scheduleQueue() {
        return new Queue(SCHEDULE_QUEUE);
    }

    @Bean
    public DirectExchange scheduleExchange() {
        return new DirectExchange(SCHEDULE_EXCHANGE, true, false);
    }

    @Bean
    public Binding scheduleBinding() {
        return BindingBuilder.bind(scheduleQueue())
                .to(scheduleExchange())
                .with(SCHEDULE_ROUTING_KEY);
    }

}
