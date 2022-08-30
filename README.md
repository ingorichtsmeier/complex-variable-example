# complex-variable-example
Example using an object variable in a process

### Hypothesis
A customer has seen additional revisions on an immutable complex process variable.

The variable is created in the beginning of the process and read in every services tasks afterwards.

But in rare situations they see OptimisticLockingExceptions where the complex varibale is involed and has a higher revision than the expected 1.

This is not reproduceable in a single test case.

We assume that this may happen on a higher volume of process instances running in a cluster.

Lets setup a cluster with Docker compose and a real database like PostgresQL and start 15000 process instances with several client threads in a loop.

This experiment should be repeatable. 

## Setup

### Build the process app
```
cd process-app
mvn clean package
```
### Create Docker container
```
docker build --tag=process-app:latest .
```

### Start Docker Compose

```
cd ..
docker-compose up --build
```

### Scale Camunda engines
```
docker-compose up --scale process-app=4 -d
```

In case of port errors, scale up slowly, one engine after the other.

## Run experiments

### Start process instances
```
curl --location --request POST 'localhost:8084/engine-rest/process-definition/key/MainProcess/start' \
--header 'Content-Type: application/json' \
--data-raw '{"variables": {
  "firstname": {"value": "1"},
  "lastname": {"value": "1"}
}}'
```

If you like multi threaded process instance start, run the Java program from 
[client](client/src/main/java/com/camunda/consulting/ProcessInstanceStarter.java).

Adjust the parameters for the REST API with port and the loops. 

### SQL statements
```
--- get process instance and activity of variable where revision > 0
select var.id_, var.proc_inst_id_, var.rev_, p.business_key_, a.act_name_
from act_hi_varinst var 
inner join act_hi_procinst p on var.proc_inst_id_ = p.id_ 
inner join act_hi_actinst a on var.act_inst_id_ = a.parent_act_inst_id_
where name_ = 'customer'
and rev_ > 0

--- get metrics from the job executor (what is running on which node)
select * from act_ru_meter_log 
where timestamp_ > '2022-08-30 21:00:00' 
order by timestamp_, name_

--- get variables with revision > 0
select id_, proc_inst_id_, rev_ from act_hi_varinst 
where name_ = 'customer' and rev_ > 0

--- get duration of some process instances
select end_time_, duration_, business_key_, proc_def_key_ 
from act_hi_procinst 
where end_time_ > '2022-08-29 19:36:40' 
and end_time_ < '2022-08-29 19:36:41'

--- get maximum duration of process instances
select start_time_, end_time_, duration_, business_key_, proc_def_key_ 
from act_hi_procinst order by duration_ desc

--- get minimum duration of process instances
select start_time_, end_time_, duration_, business_key_, proc_def_key_ 
from act_hi_procinst order by duration_ asc
```

Work only with history level `full`

```
--- where are jobs executed in the cluster
select distinct hostname_ from act_hi_job_log
```

### More ideas
* check revision of runtime variable "customer"
    - Information in act_hi_varinst available
* check if process instances switch to another engine
     - Difficult with history level audit: No entries in act_hi_joblog
* try to increase process instances running simultaneously
    - multi threaded process instance start via REST
    - longer runtime with loops
    - timers between activities
    - user tasks
* Load balancer?

### Interesting results

See [Findings](FINDINGS.md)

### Used tutorials
[Dockerizing a Spring Boot Applications](https://www.baeldung.com/dockerizing-spring-boot-application)

[Running Spring Boot with PostgreSQL in Docker Compose](https://www.baeldung.com/spring-boot-postgresql-docker)

[ExecutorService - Waiting for Threads to Finish](https://www.baeldung.com/java-executor-wait-for-threads)