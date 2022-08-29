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

