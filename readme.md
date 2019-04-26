[![Build Status](https://travis-ci.org/earth001/advanced_camel_homework_assignment.svg?branch=develop)](https://travis-ci.org/earth001/advanced_camel_homework_assignment)
# Business Scenario

This project facilitates the synchronization of master patient records across different healthcare providers. It is important to have consistent patient information across these multiple providers so that patients may receive consistent care. For that to occur, their personal and medical information needs to be shared. Updates to the patient record also need to flow across the providers to maintain accuracy and currency.

**Technical goals to accomplish the above**:

* Build a RESTful CXF service that receives the patient record and validates it.

* Publish the XML to a queue and send an acknowledgment back to the client (Done transformer in the use case document).

* Receive the message from the messaging system and invoke the backend web service. The Nextgate web service will be provided for testing.  Respond to the client using the Done transformer.

** Lab Instructions**: `https://www.opentlc.com/labs/agile_integration_advanced/06_1_Assignment_Lab.html`. The offline version in the 06_1_Business Scenario.pdf file in this repository.

# Requirements

* Maven 3.6.0

* Java 8

* ActiveMQ Broker 7.1

# Modules

* **artifacts**: WSDL and XSD files you work from

* **inbound**: Route or service that receives the patient payload

* **xlate**: Route that marshalls the Java object to XML

* **outbound**: Route that publishes the XML payload on a A-MQ queue

* **services/integration-test-server**: The Nextgate web service to test Business Scenario integration

# Build project

In order to build the project we need to run the following instruction in the root folder:

`mvn clean install -DskipTests`

# Unit testing

In order to run the unit tests we need to run the following instruction in the root folder:

`mvn test`

# Execution

## ActiveMQ Broker

To test the project we need a running instance of ActiveMQ with the following credentials:

* **User**: admin

* **Password**: password

Running instructions can be found here: https://access.redhat.com/documentation/en-us/red_hat_amq/7.1/html-single/using_amq_broker/index#creating_a_broker_instance

## Nextgate web service (integration-test-server)

In order to run the Nextgate web service we need to run the following instruction in the root folder:

`mvn spring-boot:run`

This instruction launches the server in `localhost` in with the port `8181`.

We can find the SOAP endpoint in the following URL:
`http://localhost:8181/cxf/PersonEJBService/PersonEJB?wsdl`

Additional we can found the of services here: `http://localhost:8181/cxf`

## Routes

To run the routes (inbound, outbound, xlate) we need to run the following instruction in the root folder of each one:

`mvn spring-boot:run`

### Inbound route

When we run the inbound route it create a Rest service endpoint in the following url:

`http://localhost:8080/deim/`

We can test the availability of the service call in this one
`http://localhost:8080/deim/test` that returns the word *"OK"*.

* Route's implementation: InboundRoute.java.

* Unit test: InboundRouteCamelTest.java

### Xlate route

* Route's implementation: XlateRoute.java.

* Unit test: XlateRouteCamelTest.java

### Outbound route

* Route's implementation: OutboundRoute.java.

* Unit test: OutboundRouteCamelTest.java

# End to end test

With the AMQBroker, the Nextgate server and the routes (inbound, xlate, outbound) running we can test the integration with the following command:

`curl --data "@SimplePatient.xml" http://localhost:8080/deim/add -H "Content-Type: application/xml" -v`

Note that the `SimplePatient.xml` file is located in `advanced_camel_homework_assignment/inbound/src/test/resources/SimplePatient.xml`. The curl command need to run there or we need to add the path to de `--data` option.

If the invocation is success we'll see the following output in each component:

## TODO add logs

# Design choices

The routes are implemented with the Java DSL and Spring Boot. This choice is because I feel comfortable with the Spring Boot ecosystem and I consider the Java DSL type safe.

# Improvements

* Add the fabric8-maven-plugin in order to deploy in OpenShift

* Add configuration to make de solution compatible with OSGI.

* Use the Java DSL to define the nodes in the routes, for example instead of using "cxf..." we can use the `CxfEndpoint` class.