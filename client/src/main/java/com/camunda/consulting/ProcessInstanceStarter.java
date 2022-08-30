package com.camunda.consulting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.camunda.community.rest.client.dto.ProcessInstanceWithVariablesDto;
import org.camunda.community.rest.client.invoker.ApiClient;
import org.camunda.community.rest.client.invoker.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessInstanceStarter {
  
  private static final Logger LOG = LoggerFactory.getLogger(ProcessInstanceStarter.class);
  
  public static void main(String[] args) throws Exception {
    ProcessInstanceStarter processInstanceStarter = new ProcessInstanceStarter();
    processInstanceStarter.startInstances();
  }

  public void startInstances() throws ApiException, Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    
    ApiClient client = new ApiClient();
    client.setBasePath("http://localhost:8084/engine-rest");
    for (int j = 0; j < 500; j++) {
      
      List<Callable<ProcessInstanceWithVariablesDto>> callables = new ArrayList<Callable<ProcessInstanceWithVariablesDto>>();
      for (int i = 0; i < 10; i++) {
        int index = i + j*10;
        callables.add(new Starter(client, "java_first" + index, "java_last" + index));
      }
      
      List<Future<ProcessInstanceWithVariablesDto>> processInstances = executorService.invokeAll(callables);
      
      for (int i = 0; i < 10; i++) {
        ProcessInstanceWithVariablesDto processInstance = processInstances.get(i).get();
        LOG.info("finished start of {}", processInstance.getId());
      }
    }
    
    executorService.shutdown();
    LOG.info("All shut down");    
  }

}
