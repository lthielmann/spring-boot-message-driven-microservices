# spring-boot-message-driven-microservices

Example of how to connect two separated Spring Boot Microservices via Spring AMQP and RabbitMQ.

One, the producer, is the master of customer data. Every update of a customer will
be pushed to a queue, which can be subscribed by other Microservices. The exchange format of the message body is JSON.

To make these example runnable, you have to get RabbitMQ. The binaries and an installation guide can be found [here](http://www.rabbitmq.com/download.html).

After that you can start the applications with 
```
./gradlew bootRun
```