package de.lth.examples.rabbitmq.producer.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path( "customers" )
@Consumes( MediaType.APPLICATION_JSON )
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GET
    @Path( "/actions/alive" )
    public Response isAlive() {
        return Response.ok().build();
    }

    @PUT
    @Path( "{customerNumber}" )
    public Response updateCustomer( @PathParam( "customerNumber" ) String customerNumber,
                                    Customer customer ) {
        log.info( "Incoming customer update request(customerNumber="+ customerNumber + ")." );
        customerService.updateCustomer( customer );
        return Response.noContent().build();
    }
}
