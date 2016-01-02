package de.lth.examples.rabbitmq.producer.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    static final String QUEUE_NAME = "customer-updates";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange( QUEUE_NAME + "-exchange" );
    }

    @Bean
    public Queue queue() {
        return new Queue( QUEUE_NAME, false );
    }

    @Bean
    public Binding binding( TopicExchange exchange, Queue queue) {
        return BindingBuilder.bind( queue ).to( exchange ).with( QUEUE_NAME );
    }

    @Bean
    public RabbitTemplate rabbitTemplate( ConnectionFactory connectionFactory,
                                          MessageConverter messageConverter ) {
        RabbitTemplate template = new RabbitTemplate( connectionFactory );
        template.setMessageConverter( messageConverter );
        return template;
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        // this is necessary to remove the __TypeId__ header field from the message
        converter.setJavaTypeMapper( new Jackson2JavaTypeMapperSupport() );
        return converter;
    }
}
