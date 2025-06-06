package com.mariuszilinskas.vsp.comms.email.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.queues.platform-emails}")
    private String platformEmailsQueue;

    @Value("${rabbitmq.routing-keys.platform-emails}")
    private String platformEmailsRoutingKey;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue platformEmailsQueue() {
        return new Queue(platformEmailsQueue, true);
    }

    @Bean
    public Binding createPlatformEmailsBinding() {
        return BindingBuilder.bind(platformEmailsQueue())
                .to(exchange())
                .with(platformEmailsRoutingKey);
    }

    @Bean
    public MessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
