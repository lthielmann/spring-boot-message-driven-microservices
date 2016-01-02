package de.lth.examples.rabbitmq.producer.customer;

import lombok.Data;

@Data
public class Customer {
    private String customerNumber;
    private String firstName;
    private String lastName;
}
