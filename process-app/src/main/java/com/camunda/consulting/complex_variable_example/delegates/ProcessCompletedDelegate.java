package com.camunda.consulting.complex_variable_example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcessCompletedDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(ProcessCompletedDelegate.class);


  @Override
  public void execute(DelegateExecution execution) throws Exception {
    execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("sideProcessCompletedMessage")
        .processInstanceBusinessKey(execution.getBusinessKey()).correlate();
    LOG.info("main process continue");
  }

}
