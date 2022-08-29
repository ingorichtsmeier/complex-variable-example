package com.camunda.consulting.complex_variable_example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;

@Component
public class StartSideProcessDelegate implements JavaDelegate {

  public void execute(DelegateExecution execution) throws Exception {
    ProcessInstance sideProcess = execution.getProcessEngineServices().getRuntimeService()
        .createMessageCorrelation("sideProcessMessage")
        .processInstanceBusinessKey(execution.getBusinessKey())
        .correlateStartMessage();

    execution.setVariable("sideProcess", sideProcess.getId());
  }

}
