package de.lth.examples.rabbitmq.producer.messaging;

import com.fasterxml.jackson.databind.JavaType;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;

class Jackson2JavaTypeMapperSupport implements Jackson2JavaTypeMapper {
    @Override
    public void fromJavaType( JavaType javaType, MessageProperties properties ) {
    }

    @Override
    public JavaType toJavaType( MessageProperties properties ) {
        return null;
    }

    @Override
    public void fromClass( Class< ? > clazz, MessageProperties properties ) {
    }

    @Override
    public Class< ? > toClass( MessageProperties properties ) {
        return null;
    }
}
