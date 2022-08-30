package com.camunda.consulting;

import java.util.Map;
import java.util.concurrent.Callable;

import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.dto.ProcessInstanceWithVariablesDto;
import org.camunda.community.rest.client.dto.StartProcessInstanceDto;
import org.camunda.community.rest.client.dto.VariableValueDto;
import org.camunda.community.rest.client.invoker.ApiClient;
import org.camunda.community.rest.client.invoker.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Starter implements Callable<ProcessInstanceWithVariablesDto> {
  
  private ApiClient client;
  private String firstname;
  private String lastname;
  private static final Logger LOG = LoggerFactory.getLogger(Starter.class);

  public Starter(ApiClient client, String firstname, String lastname) {
    super();
    this.client = client;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  @Override
  public ProcessInstanceWithVariablesDto call() throws Exception {
    return startProcessInstance(client, firstname, lastname);
  }
  
  public ProcessInstanceWithVariablesDto startProcessInstance(ApiClient client, String firstname, String lastname) throws ApiException {
    LOG.info("Begin for {} {}", firstname, lastname);
    StartProcessInstanceDto startProcessInstanceDto = new StartProcessInstanceDto();
    startProcessInstanceDto.setVariables(Map.of("firstname", new VariableValueDto().value(firstname), 
        "lastname", new VariableValueDto().value(lastname)));
    ProcessInstanceWithVariablesDto processInstance = new ProcessDefinitionApi(client)
        .startProcessInstanceByKey("MainProcess", startProcessInstanceDto);
    LOG.info("started: {}", processInstance.getId());
    return processInstance;
  }


}
