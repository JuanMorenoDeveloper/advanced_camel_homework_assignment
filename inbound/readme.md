# Module description

Contains the core business logic of the application and top-level concerns of the application only.

Should be free of resource and component code tied with different technology stacks. 

Use code Annotations and maven+spring dependency injection to tie in or bind processors, services, components, resources.  

## Usage

This project starts a REST server at http://localhost:8080 and exposes our service at /deim/ with the methods method being 'add' and 'test'. The REST service (DEIMServiceImpl.java) processes the message (match method) by sending it to our first Camel Route (InboundRoute.java). 

The InboundRoute.java marshals the input and sends to the mq:q.empi.deim.in queue. For the sake of the demo the InboundRoute is set to operate synchronously, so we set the pattern to InOut (It could be improved in order to use asynchronous calls). In other words we want Camel to set up a return transport and request a response for us. 

The main logic for the REST service can be found in the src/main/java/com/redhat/usecase/service/impl/DEIMServiceImpl.java file. We receive the message, build a response, send the message to the Camel InboundRoute.java and then set the response based on the code returned to us. Finally we send the response back to the caller. If you're feeling brave, you can modify this to send back different messages or codes.
