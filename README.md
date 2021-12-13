# event-stream-demo
Event Stream Demo: inspired by [Red Hat event streaming workshop](https://github.com/RedHat-Middleware-Workshops/dayinthelife-streaming)

# Microservices Architecture

## Deploy Qaurkus Services

* Quarkus service A
  * Package Qaurkus service inventory under inventory project
    ```
    mvn clean compile package -DskipTests
    ``` 
  * Deploy on OpenShift
    ``` 
    # create build config
    oc new-build registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift:1.5 --binary --name=inventory -l app=inventory
    
    # start build
    oc start-build inventory --from-file ./target/*-runner.jar --follow
    
    # create service and expose route
    oc new-app inventory -l 'app.openshift.io/runtime=quarkus,app.kubernetes.io/part-of=rest'; oc expose svc/inventory
    ``` 
* Quarkus service B
  * Package Qaurkus service inventory under invoice project
    ```
    mvn clean compile package -DskipTests
    ``` 
  * Deploy on OpenShift
    ``` 
    # create build config
    oc new-build registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift:1.5 --binary --name=invoice -l app=invoice
    
    # start build
    oc start-build invoice --from-file ./target/*-runner.jar --follow
    
    # create service and expose route
    oc new-app invoice -l 'app.openshift.io/runtime=quarkus,app.kubernetes.io/part-of=rest'; oc expose svc/invoice
    ``` 
* Spring Boot Service
  * Package Qaurkus service inventory under invoice project
    ```
    mvn clean compile package -DskipTests
    ``` 
  * Deploy on OpenShift
    ``` 
    # create build config
    oc new-build registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift:1.5 --binary --name=invoice -l app=invoice
    
    # start build
    oc start-build invoice --from-file ./target/*-runner.jar --follow
    
    # create service and expose route
    oc new-app invoice -l 'app.openshift.io/runtime=quarkus,app.kubernetes.io/part-of=rest'; oc expose svc/invoice
    ``` 
