package de.lth.examples.rabbitmq.producer.customer;

public interface UpdateCustomerEventListener {
    void customerUpdated( Customer customer );
}
