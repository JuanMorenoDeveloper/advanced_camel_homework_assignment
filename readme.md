[![Build Status](https://travis-ci.org/JuanMorenoDeveloper/advanced_camel_homework_assignment.svg?branch=master)](https://travis-ci.org/JuanMorenoDeveloper/advanced_camel_homework_assignment)
# Business Scenario

This project facilitates the synchronization of master patient records across different healthcare providers. It is important to have consistent patient information across these multiple providers so that patients may receive consistent care. For that to occur, their personal and medical information needs to be shared. Updates to the patient record also need to flow across the providers to maintain accuracy and currency.

**Technical goals to accomplish the above**:

* Build a RESTful CXF service that receives the patient record and validates it.

* Publish the XML to a queue and send an acknowledgment back to the client (Done transformer in the use case document).

* Receive the message from the messaging system and invoke the backend web service. The Nextgate web service will be provided for testing.  Respond to the client using the Done transformer.

** Lab Instructions**: `https://www.opentlc.com/labs/agile_integration_advanced/06_1_Assignment_Lab.html`. The offline version in the 06_1_Business Scenario.pdf file in this repository.

# Requirements

* Maven 3.6.0

* Java 8/11

* ActiveMQ Broker 7.2

# Modules

* **artifacts**: WSDL and XSD files you work from

* **inbound**: Route or service that receives the patient payload

* **xlate**: Route that marshalls the Java object to XML

* **outbound**: Route that publishes the XML payload on a A-MQ queue

* **services/integration-test-server**: The Nextgate web service to test Business Scenario integration

# Configuration

Basic configuration like broker-url, user, password is located in the application.yml files of each module.

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

The sequence is (in /opt/amq-broker-7.2.4/bin folder):

1. Create broker: `$ ./artemis create mybroker3` 

2. Apply configuration: user-> `admin`, password->`password`

3. Run broker: `$ "/opt/amq-broker-7.2.4/bin/mybroker3/bin/artemis" run`

## Nextgate web service (integration-test-server)

In order to run the Nextgate web service we need to run the following instruction in the integration-test-server root folder:

`mvn spring-boot:run`

This instruction launches the server in `localhost` with the port `8181`.

We can find the SOAP endpoint in the following URL:
`http://localhost:8181/cxf/PersonEJBService/PersonEJB?wsdl`

Additionally, we can found the list of services here: `http://localhost:8181/cxf`

## Routes

To run the routes (inbound, outbound, xlate) we need to run the following instruction in the root folder of each one:

`mvn spring-boot:run`

### Inbound route

When we run the inbound route; it creates a Rest service endpoint in the following url:

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

Note that the `SimplePatient.xml` file is located in `advanced_camel_homework_assignment/inbound/src/test/resources/SimplePatient.xml`. The `curl` command need to run there or we need to add the path to the `--data` option.

If the invocation is successful, we'll see the following output in each component:

* Curl Response:

```
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /deim/add HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.58.0
> Accept: */*
> Content-Type: application/xml
> Content-Length: 470
> 
* upload completely sent off: 470 out of 470 bytes
< HTTP/1.1 200 
< Date: Sat, 27 Apr 2019 02:32:06 GMT
< Content-Type: text/plain
< Content-Length: 4
< 
* Connection #0 to host localhost left intact
DONE%
```

* Inbound route:

```
2019-04-26 23:32:05.900  INFO 6669 --- [nio-8080-exec-2] InboundRoute                             : inbound route
2019-04-26 23:32:05.916  INFO 6669 --- [nio-8080-exec-2] com.company.app                          : Exchange[
, Id: ID-msi-laptop-1556332172304-0-1
, ExchangePattern: InOut
, Properties: {CamelCreatedTimestamp=Fri Apr 26 23:32:05 UYT 2019, CamelExternalRedelivered=false, CamelMessageHistory=[DefaultMessageHistory[routeId=InboundRoute, node=log1], DefaultMessageHistory[routeId=InboundRoute, node=marshal1], DefaultMessageHistory[routeId=InboundRoute, node=to1]], CamelToEndpoint=log://com.company.app?multiline=true&showAll=true}
, Headers: {breadcrumbId=ID-msi-laptop-1556332172304-0-1, Content-Type=application/xml}
, BodyType: byte[]
, Body: <?xml version="1.0" encoding="UTF-8" standalone="yes"?><Person xmlns="http://www.app.customer.com">    <age>30</age>    <legalname>        <given>First</given>        <family>Last</family>    </legalname>    <fathername>Dad</fathername>    <mothername>Mom</mothername>    <gender>        <code>Male</code>    </gender></Person>
, Out: null: 
]
2019-04-26 23:32:06.120  INFO 6669 --- [ActiveMQ Task-1] o.a.a.t.failover.FailoverTransport       : Successfully connected to tcp://127.0.0.1:61616
```

* Xlate route:

```
2019-04-26 23:32:06.301  INFO 5845 --- [q.empi.deim.in]] XlateRoute                               : xlate
2019-04-26 23:32:06.595  INFO 5845 --- [q.empi.deim.in]] o.a.camel.converter.jaxp.StaxConverter   : Created XMLInputFactory: com.sun.xml.internal.stream.XMLInputFactoryImpl@66bc3437. DOMSource/DOMResult may have issues with com.sun.xml.internal.stream.XMLInputFactoryImpl@66bc3437. We suggest using Woodstox.
2019-04-26 23:32:06.723  INFO 5845 --- [q.empi.deim.in]] com.company.app                          : Exchange[
, Id: ID-msi-laptop-1556331995863-0-1
, ExchangePattern: InOnly
, Properties: {CamelBinding=org.apache.camel.component.jms.JmsBinding@23b1443d, CamelCreatedTimestamp=Fri Apr 26 23:32:06 UYT 2019, CamelExternalRedelivered=false, CamelMessageHistory=[DefaultMessageHistory[routeId=XlateRoute, node=log1], DefaultMessageHistory[routeId=XlateRoute, node=unmarshal1], DefaultMessageHistory[routeId=XlateRoute, node=to1]], CamelToEndpoint=log://com.company.app?multiline=true&showAll=true}
, Headers: {__AMQ_CID=ID:msi-laptop-46523-1556332325942-0:1, breadcrumbId=ID-msi-laptop-1556332172304-0-1, Content-Type=application/xml, JMSCorrelationID=null, JMSCorrelationIDAsBytes=null, JMSDeliveryMode=2, JMSDestination=queue://q.empi.deim.in, JMSExpiration=0, JMSMessageID=ID:msi-laptop-46523-1556332325942-1:1:1:1:1, JMSPriority=4, JMSRedelivered=false, JMSReplyTo=null, JMSTimestamp=1556332326215, JMSType=null, JMSXGroupID=null, JMSXUserID=null}
, BodyType: com.customer.app.Person
, Body: <?xml version="1.0" encoding="UTF-8" standalone="yes"?><Person xmlns="http://www.app.customer.com">    <age>30</age>    <legalname>        <given>First</given>        <family>Last</family>    </legalname>    <fathername>Dad</fathername>    <mothername>Mom</mothername>    <gender>        <code>Male</code>    </gender></Person>
, Out: null: 
]
```

* Outbound route:

```
2019-04-26 23:32:06.771  INFO 6100 --- [.empi.deim.out]] OutboundRoute                            : outbound
2019-04-26 23:32:06.893  INFO 6100 --- [.empi.deim.out]] com.company.app                          : Exchange[
, Id: ID-msi-laptop-1556331995697-0-1
, ExchangePattern: InOnly
, Properties: {CamelBinding=org.apache.camel.component.jms.JmsBinding@352f1083, CamelCreatedTimestamp=Fri Apr 26 23:32:06 UYT 2019, CamelExternalRedelivered=false, CamelMessageHistory=[DefaultMessageHistory[routeId=OutboundRoute, node=log1], DefaultMessageHistory[routeId=OutboundRoute, node=unmarshal1], DefaultMessageHistory[routeId=OutboundRoute, node=to1]], CamelToEndpoint=log://com.company.app?multiline=true&showAll=true}
, Headers: {__AMQ_CID=ID:msi-laptop-44725-1556331999679-0:1, breadcrumbId=ID-msi-laptop-1556332172304-0-1, CamelJmsDeliveryMode=2, Content-Type=application/xml, JMSCorrelationID=null, JMSCorrelationIDAsBytes=null, JMSDeliveryMode=2, JMSDestination=queue://q.empi.deim.out, JMSExpiration=0, JMSMessageID=ID:msi-laptop-44725-1556331999679-1:1:1:1:1, JMSPriority=4, JMSRedelivered=false, JMSReplyTo=null, JMSTimestamp=1556332326735, JMSType=null, JMSXGroupID=null, JMSXUserID=null}
, BodyType: com.sun.mdm.index.webservice.ExecuteMatchUpdate
, Body: <?xml version="1.0" encoding="UTF-8" standalone="yes"?><ns2:executeMatchUpdate xmlns:ns2="http://webservice.index.mdm.sun.com/">    <callerInfo>        <application>App</application>        <applicationFunction>Function</applicationFunction>        <authUser>Xlate</authUser>    </callerInfo>    <sysObjBean>        <person>            <fatherName>Dad</fatherName>            <firstName>First</firstName>            <gender>Male</gender>        </person>    </sysObjBean></ns2:executeMatchUpdate>
, Out: null: 
]
```

* Nextgate server (integration-test-server)

```
2019-04-26 23:32:07.157  INFO 7131 --- [nio-8181-exec-1] o.apache.cxf.services.PersonEJB.REQ_IN   : REQ_IN
    Address: http://localhost:8181/cxf/PersonEJBService/PersonEJB
    HttpMethod: POST
    Content-Type: text/xml; charset=UTF-8
    ExchangeId: 298365ba-734b-492a-b135-27ce28542d93
    ServiceName: PersonEJBImplService
    PortName: PersonEJBImplPort
    PortTypeName: PersonEJB
    Headers: {SOAPAction="http://webservice.index.mdm.sun.com/PersonEJB/executeMatchUpdateRequest", Accept=*/*, jmsmessageid=ID:msi-laptop-44725-1556331999679-1:1:1:1:1, jmspriority=4, jmsexpiration=0, jmsdestination=queue://q.empi.deim.out, jmsredelivered=false, pragma=no-cache, __amq_cid=ID:msi-laptop-44725-1556331999679-0:1, jmsdeliverymode=2, jmstimestamp=1556332326735, breadcrumbid=ID-msi-laptop-1556332172304-0-1, host=localhost:8181, connection=keep-alive, content-type=text/xml; charset=UTF-8, cache-control=no-cache, Content-Length=563, user-agent=Apache-CXF/3.2.7}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:executeMatchUpdate xmlns:ns2="http://webservice.index.mdm.sun.com/">
    <callerInfo>
        <application>App</application>
        <applicationFunction>Function</applicationFunction>
        <authUser>Xlate</authUser>
    </callerInfo>
    <sysObjBean>
        <person>
            <fatherName>Dad</fatherName>
            <firstName>First</firstName>
            <gender>Male</gender>
        </person>
    </sysObjBean>
</ns2:executeMatchUpdate></soap:Body></soap:Envelope>

2019-04-26 23:32:07.163  INFO 7131 --- [nio-8181-exec-1] com.company.app.test.PersonEJBImpl       : Received payload in Nextgate Test Server
2019-04-26 23:32:07.163  INFO 7131 --- [nio-8181-exec-1] com.company.app.test.PersonEJBImpl       : Caller Info: Application = App User = Xlate
2019-04-26 23:32:07.163  INFO 7131 --- [nio-8181-exec-1] com.company.app.test.PersonEJBImpl       : SystemPerson: FirstName = First Gender = Male
2019-04-26 23:32:07.198  INFO 7131 --- [nio-8181-exec-1] o.a.cxf.services.PersonEJB.RESP_OUT      : RESP_OUT
    Content-Type: text/xml
    ResponseCode: 200
    ExchangeId: 298365ba-734b-492a-b135-27ce28542d93
    ServiceName: PersonEJBImplService
    PortName: PersonEJBImplPort
    PortTypeName: PersonEJB
    Headers: {}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:executeMatchUpdateResponse xmlns:ns2="http://webservice.index.mdm.sun.com/"><return><EUID>Xlate</EUID><matchFieldChanged>false</matchFieldChanged><overlayDetected>false</overlayDetected><resultCode>1</resultCode></return></ns2:executeMatchUpdateResponse></soap:Body></soap:Envelope>
```

* Counter in artemis management console:

![](/images/q.empi.deim.in-count.png)

*q.empi.deim.in message count*

![](/images/q.empi.deim.out-count.png)

*q.empi.deim.out message count*

# Design choices

The routes are implemented with the Java DSL and Spring Boot. This choice is because I feel comfortable with the Spring Boot ecosystem and I consider the Java DSL type safe.

# Improvements

* Add the fabric8-maven-plugin in order to deploy in OpenShift

* Add configuration to make de solution compatible with OSGI.

* Use the Java DSL to define the nodes in the routes, for example instead of using "cxf..." we can use the `CxfEndpoint` class.

* Run performance tests in order to measure request/s

* Add spring actuator in order to monitor the route's health 

* Use static analysis tools in the project (PMD, CheckStyle, SpotBugs)
