package de.lth.examples.rabbitmq.producer.customer;

import de.lth.examples.rabbitmq.producer.Application;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( Application.class )
@WebIntegrationTest
public class CustomerResourceIT {

    @Test
    public void testUpdateCustomer() {
        Customer testCustomer = new Customer();
        testCustomer.setCustomerNumber( "123" );
        testCustomer.setFirstName( "John" );
        testCustomer.setLastName( "Doe" );

        Client client = new JerseyClientBuilder().build();
        WebTarget target = client.target( "http://localhost:1234/api" )
                .path( "customers" )
                .path( "{customerNumber}" )
                .resolveTemplate( "customerNumber", testCustomer.getCustomerNumber() );
        Response response = target.request().put( Entity.json( testCustomer ) );
        assertEquals( "status code", response.getStatus(), Response.Status.NO_CONTENT.getStatusCode() );
    }
}