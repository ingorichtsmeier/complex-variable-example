package com.camunda.consulting.complex_variable_example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.camunda.consulting.complex_variable_example.data.Customer;

@Component
public class ReadCustomerAddressDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(ReadCustomerAddressDelegate.class);


  public void execute(DelegateExecution execution) throws Exception {
    Customer customer = (Customer) execution.getVariable("customer");
    
    LOG.info("Address: {}", customer.getAddress());
    
    execution.setVariable("addressRead", true);
  }

}
