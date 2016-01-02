package de.lth.examples.rabbitmq.producer.messaging;

import de.lth.examples.rabbitmq.producer.customer.Customer;
import de.lth.examples.rabbitmq.producer.customer.UpdateCustomerEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueUpdateCustomerEventsListener implements UpdateCustomerEventListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void customerUpdated( Customer customer ) {
        rabbitTemplate.convertAndSend( AmqpConfig.QUEUE_NAME, customer );
        log.info("Pushed customer to queue: ");
    }
}
