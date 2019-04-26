package com.redhat.route;

import com.customer.app.Person;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.stereotype.Component;

@Component
public class InboundRoute extends RouteBuilder {

  @Override
  public void configure() {
    JaxbDataFormat personDataFormat = new JaxbDataFormat();
    personDataFormat.setContextPath(Person.class.getPackage().getName());
    personDataFormat.setPrettyPrint(true);
    from("direct:start").id("InboundRoute")
        .log("inbound route")
        .marshal(personDataFormat)
        .to("log:com.company.app?showAll=true&multiline=true")
        .convertBodyTo(String.class)
        .inOnly("mq:q.empi.deim.in")
        .transform(constant("DONE"));
  }
}
