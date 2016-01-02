package de.lth.examples.rabbitmq.producer;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath( "/api" )
public class ApplicationConfiguration extends ResourceConfig {

    public ApplicationConfiguration() {
        packages( "de.lth.examples.rabbitmq.producer" );
    }
}
