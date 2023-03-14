package com.yuge.ing.amqp.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: yuge
 * @date: 2023/2/23
 **/
@Configuration
public class DirectConfig {

    public static final String DIRECT_QUEUE = "sms.direct.queue";

    public static final String DIRECT_EXCHANGE = "sms.direct.exchange";

    public static final String DIRECT_DEFAULT_ROUTING_KEY = "sms.default";

    // RabbitListener中声明通过注解的方式声明
//    @Bean
//    public Queue smsDirectQueue() {
//        return new Queue(DIRECT_QUEUE);
//    }
//
//    @Bean
//    public DirectExchange smsDirectExchange() {
//        return new DirectExchange(DIRECT_EXCHANGE, true, false);
//    }
//
//    @Bean
//    public Binding smsDirectBinding() {
//        return BindingBuilder.bind(smsDirectQueue())
//                .to(smsDirectExchange())
//                .with(DIRECT_DEFAULT_ROUTING_KEY);
//    }

}
