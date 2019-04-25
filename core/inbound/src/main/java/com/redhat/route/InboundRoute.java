package com.redhat.route;


import com.customer.app.Person;
import com.redhat.converter.MyTypeConverter;
import com.sun.mdm.index.webservice.ExecuteMatchUpdate;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InboundRoute extends RouteBuilder {

  @Autowired
  CamelContext context;

  @Override
  public void configure() {

    JaxbDataFormat personDataFormat = new JaxbDataFormat();
    personDataFormat.setContextPath(Person.class.getPackage().getName());
    personDataFormat.setPrettyPrint(true);

    JaxbDataFormat nextDataFormat = new JaxbDataFormat();
    nextDataFormat.setContextPath(ExecuteMatchUpdate.class.getPackage().getName());
    nextDataFormat.setPrettyPrint(true);

    context.getTypeConverterRegistry().addTypeConverter(ExecuteMatchUpdate.class, Person.class, new MyTypeConverter());

    from("direct:start")
        .log("inbound route")
        .marshal(personDataFormat)
        .to("log:com.company.app?showAll=true&multiline=true")
        .convertBodyTo(String.class)
        .inOnly("mq:q.empi.deim.in")
        .transform(constant("done - inbound"));

//    from("mq:q.empi.deim.in")
//        .log("xlate")
//        .unmarshal(personDataFormat)
//        .to("log:com.company.app?showAll=true&multiline=true")
//        .convertBodyTo(ExecuteMatchUpdate.class)
//        .marshal(nextDataFormat)
//        .inOnly("mq:q.empi.deim.out")
//        .transform(constant("doneeee"));
  }
}
