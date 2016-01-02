package de.lth.examples.rabbitmq.producer.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
public class CustomerService {

    private final Collection< UpdateCustomerEventListener > eventListener = new ArrayList<>();

    @Autowired
    public void addUpdateCustomerEventListener( UpdateCustomerEventListener eventListener ) {
        this.eventListener.add( eventListener );
    }

    public void updateCustomer( Customer customer ) {
        log.info( "updateCustomer(): " + customer );
        fireCustomerUpdatedEvent( customer );
    }

    protected void fireCustomerUpdatedEvent( Customer customer ) {
        for ( UpdateCustomerEventListener listener : eventListener ) {
            listener.customerUpdated( customer );
        }
    }
}
