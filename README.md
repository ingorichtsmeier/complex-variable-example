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

### Start process instances
```
curl --location --request POST 'localhost:8084/engine-rest/process-definition/key/MainProcess/start' \
--header 'Content-Type: application/json' \
--data-raw '{"variables": {
  "firstname": {"value": "1"},
  "lastname": {"value": "1"}
}}'
```

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

### Interesting results

Jobs are only executed on 2 of 4 nodes:

----------------------------------------------------------------------------------------------------------------------------------------------
| id_                                  | name_                       | reporter_          | value_ | timestamp_              | milliseconds_ |
| ------------------------------------ | --------------------------- | ------------------ | ------ | ----------------------- | ------------- |
| 3f3709d6-28b0-11ed-9468-0242ac1e0003 | activity-instance-end       | 172.30.0.3$default | 4577   | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d1-28b0-11ed-9468-0242ac1e0003 | activity-instance-start     | 172.30.0.3$default | 4582   | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d5-28b0-11ed-9468-0242ac1e0003 | executed-decision-elements  | 172.30.0.3$default | 0      | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709da-28b0-11ed-9468-0242ac1e0003 | executed-decision-instances | 172.30.0.3$default | 0      | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d2-28b0-11ed-9468-0242ac1e0003 | job-acquired-failure        | 172.30.0.3$default | 89     | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d8-28b0-11ed-9468-0242ac1e0003 | job-acquired-success        | 172.30.0.3$default | 893    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d9-28b0-11ed-9468-0242ac1e0003 | job-acquisition-attempt     | 172.30.0.3$default | 426    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d4-28b0-11ed-9468-0242ac1e0003 | job-execution-rejected      | 172.30.0.3$default | 464    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709db-28b0-11ed-9468-0242ac1e0003 | job-failed                  | 172.30.0.3$default | 0      | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d3-28b0-11ed-9468-0242ac1e0003 | job-locked-exclusive        | 172.30.0.3$default | 898    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d7-28b0-11ed-9468-0242ac1e0003 | job-successful              | 172.30.0.3$default | 1791   | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d0-28b0-11ed-9468-0242ac1e0003 | root-process-instance-start | 172.30.0.3$default | 949    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 49a57a2c-28b0-11ed-9715-0242ac1e0004 | activity-instance-end       | 172.30.0.4$default | 423    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a27-28b0-11ed-9715-0242ac1e0004 | activity-instance-start     | 172.30.0.4$default | 418    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2b-28b0-11ed-9715-0242ac1e0004 | executed-decision-elements  | 172.30.0.4$default | 0      | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a30-28b0-11ed-9715-0242ac1e0004 | executed-decision-instances | 172.30.0.4$default | 0      | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a28-28b0-11ed-9715-0242ac1e0004 | job-acquired-failure        | 172.30.0.4$default | 166    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2e-28b0-11ed-9715-0242ac1e0004 | job-acquired-success        | 172.30.0.4$default | 107    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2f-28b0-11ed-9715-0242ac1e0004 | job-acquisition-attempt     | 172.30.0.4$default | 113    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2a-28b0-11ed-9715-0242ac1e0004 | job-execution-rejected      | 172.30.0.4$default | 15     | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a31-28b0-11ed-9715-0242ac1e0004 | job-failed                  | 172.30.0.4$default | 0      | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a29-28b0-11ed-9715-0242ac1e0004 | job-locked-exclusive        | 172.30.0.4$default | 102    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2d-28b0-11ed-9715-0242ac1e0004 | job-successful              | 172.30.0.4$default | 209    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a26-28b0-11ed-9715-0242ac1e0004 | root-process-instance-start | 172.30.0.4$default | 51     | 2022-08-30 22:08:33.328 | 1661897313328 |
| 543248b0-28b0-11ed-b7d1-0242ac1e0005 | activity-instance-end       | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248ab-28b0-11ed-b7d1-0242ac1e0005 | activity-instance-start     | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248af-28b0-11ed-b7d1-0242ac1e0005 | executed-decision-elements  | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b4-28b0-11ed-b7d1-0242ac1e0005 | executed-decision-instances | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248ac-28b0-11ed-b7d1-0242ac1e0005 | job-acquired-failure        | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b2-28b0-11ed-b7d1-0242ac1e0005 | job-acquired-success        | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b3-28b0-11ed-b7d1-0242ac1e0005 | job-acquisition-attempt     | 172.30.0.5$default | 15     | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248ae-28b0-11ed-b7d1-0242ac1e0005 | job-execution-rejected      | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b5-28b0-11ed-b7d1-0242ac1e0005 | job-failed                  | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248ad-28b0-11ed-b7d1-0242ac1e0005 | job-locked-exclusive        | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b1-28b0-11ed-b7d1-0242ac1e0005 | job-successful              | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248aa-28b0-11ed-b7d1-0242ac1e0005 | root-process-instance-start | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 56cc54b5-28b0-11ed-b57a-0242ac1e0006 | activity-instance-end       | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b0-28b0-11ed-b57a-0242ac1e0006 | activity-instance-start     | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b4-28b0-11ed-b57a-0242ac1e0006 | executed-decision-elements  | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b9-28b0-11ed-b57a-0242ac1e0006 | executed-decision-instances | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b1-28b0-11ed-b57a-0242ac1e0006 | job-acquired-failure        | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b7-28b0-11ed-b57a-0242ac1e0006 | job-acquired-success        | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b8-28b0-11ed-b57a-0242ac1e0006 | job-acquisition-attempt     | 172.30.0.6$default | 15     | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b3-28b0-11ed-b57a-0242ac1e0006 | job-execution-rejected      | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54ba-28b0-11ed-b57a-0242ac1e0006 | job-failed                  | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b2-28b0-11ed-b57a-0242ac1e0006 | job-locked-exclusive        | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b6-28b0-11ed-b57a-0242ac1e0006 | job-successful              | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc2d9f-28b0-11ed-b57a-0242ac1e0006 | root-process-instance-start | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
----------------------------------------------------------------------------------------------------------------------------------------------


### Used tutorials
[Dockerizing a Spring Boot Applications](https://www.baeldung.com/dockerizing-spring-boot-application)

[Running Spring Boot with PostgreSQL in Docker Compose](https://www.baeldung.com/spring-boot-postgresql-docker)

[ExecutorService – Waiting for Threads to Finish](https://www.baeldung.com/java-executor-wait-for-threads)