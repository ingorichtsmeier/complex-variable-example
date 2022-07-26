package com.camunda.consulting;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.camunda.consulting.complex_variable_example.CamundaApplication;

@SpringBootTest(classes = CamundaApplication.class)
public class HappyPathIntegrationTest {
  
  @Autowired
  ProcessEngine processEngine;
  
  @Test
  public void runMainProcess() {
    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("MainProcess", Map.of("firstname", "Integration", "lastname", "Test"));
    assertThat(processInstance).isActive();
  }

}
