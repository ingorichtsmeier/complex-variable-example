package com.camunda.consulting.complex_variable_example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.camunda.consulting.complex_variable_example.data.Customer;

@Component
public class AccessCustomerDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(AccessCustomerDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    ProcessInstance mainProcessInstance = execution.getProcessEngineServices().getRuntimeService()
        .createProcessInstanceQuery().processDefinitionKey("MainProcess")
        .processInstanceBusinessKey(execution.getBusinessKey()).singleResult();

    VariableInstance customerVariableInstance = execution.getProcessEngineServices().getRuntimeService()
        .createVariableInstanceQuery().variableName("customer").processInstanceIdIn(mainProcessInstance.getId())
        .singleResult();

    Customer customer = (Customer) customerVariableInstance.getValue();
    
    LOG.info("Customer from main: {}", customer);
    
    execution.setVariable("customerName", customer.getFirstName() + " " + customer.getLastName());
  }

}
