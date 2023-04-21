package com.yuge.ing.amqp.config;

import com.yuge.ing.amqp.callback.MqReturnCallback;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.CorrelationDataPostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yuge
 * @date: 2023/3/9
 **/
@Configuration
public class AmqpConfig {

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(RabbitTemplateConfigurer configurer,
                                         ConnectionFactory connectionFactory,
                                         RabbitTemplate.ConfirmCallback mqConfirmCallback,
                                         RabbitTemplate.ReturnsCallback mqReturnCallback,
                                         CorrelationDataPostProcessor mqCorrelationDataPostProcessor) {
        RabbitTemplate template = new RabbitTemplate();
        configurer.configure(template, connectionFactory);
        template.setConfirmCallback(mqConfirmCallback);
        template.setReturnsCallback(mqReturnCallback);
        template.setCorrelationDataPostProcessor(mqCorrelationDataPostProcessor);
        return template;
    }

}
