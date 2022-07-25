package com.camunda.consulting.complex_variable_example.delegates;

import java.time.LocalDate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.camunda.consulting.complex_variable_example.data.Address;
import com.camunda.consulting.complex_variable_example.data.Customer;

@Component
public class CreateCustomerDelegate implements JavaDelegate {

  private static final Logger LOG = LoggerFactory.getLogger(CreateCustomerDelegate.class);

  public void execute(DelegateExecution execution) throws Exception {
    String firstName = (String) execution.getVariable("firstname");
    String lastName = (String) execution.getVariable("lastname");
    
    Address address = new Address("Zossener Str.", "55-58", "Berlin", "10961", "Deutschland");
    Customer customer = new Customer(firstName, lastName, address, LocalDate.of(1972, 6, 12));
    
    LOG.info("Creating customer {}", customer);
    
    execution.setVariable("customer", Variables.objectValue(customer).serializationDataFormat(SerializationDataFormats.JAVA).create());

    LOG.info("BusinessKey: {} {}", firstName, lastName);
    execution.setProcessBusinessKey(firstName + " " + lastName);
  }

}
