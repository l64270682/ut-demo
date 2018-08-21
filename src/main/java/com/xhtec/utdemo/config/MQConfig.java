package com.xhtec.utdemo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hhy@100fen.cn
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq.queue")
public class MQConfig {

    private String payEvents = "pay_events";

    public String getPayEvents() {
        return payEvents;
    }

    public void setPayEvents(String payEvents) {
        this.payEvents = payEvents;
    }

    @Bean
    public Queue payQueue() {
        return new Queue(getPayEvents());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
