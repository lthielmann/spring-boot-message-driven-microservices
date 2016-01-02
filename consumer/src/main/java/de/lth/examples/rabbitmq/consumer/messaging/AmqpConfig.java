package de.lth.examples.rabbitmq.consumer.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    static final String QUEUE_NAME = "customer-updates";

    @Bean
    public Queue queue() {
        return new Queue( QUEUE_NAME, false );
    }

    @Bean
    public SimpleMessageListenerContainer container( ConnectionFactory connectionFactory,
                                                     MessageListenerAdapter listenerAdapter,
                                                     MessageConverter messageConverter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory( connectionFactory );
        container.setQueueNames( QUEUE_NAME );
        container.setMessageListener( listenerAdapter );
        return container;
    }

    @Bean
    public MessageConverter messageConverter() {
        DefaultClassMapper mapper = new DefaultClassMapper();
        mapper.setDefaultType( Customer.class );

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper( mapper );
        return converter;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter( CustomerUpdateQueueListener queueListener,
                                                   MessageConverter messageConverter ) {
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter( queueListener, "customerUpdateReceived" );
        listenerAdapter.setMessageConverter( messageConverter );
        return listenerAdapter;
    }
}
