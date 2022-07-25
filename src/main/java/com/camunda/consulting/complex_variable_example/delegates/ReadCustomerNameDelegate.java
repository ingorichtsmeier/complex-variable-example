package com.camunda.consulting.complex_variable_example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.camunda.consulting.complex_variable_example.data.Customer;

@Component
public class ReadCustomerNameDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(ReadCustomerNameDelegate.class);


  public void execute(DelegateExecution execution) throws Exception {
    Customer customer = (Customer) execution.getVariable("customer");

    LOG.info("Name: {} {}", customer.getFirstName(), customer.getLastName());
    
    execution.setVariable("customerRead", true);
  }

}
