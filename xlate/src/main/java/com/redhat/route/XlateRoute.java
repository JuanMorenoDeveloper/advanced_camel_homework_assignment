package com.redhat.route;

import com.customer.app.Person;
import com.redhat.customer.translate.TransformToExecuteMatch;
import com.sun.mdm.index.webservice.ExecuteMatchUpdate;
import javax.xml.bind.UnmarshalException;
import org.apache.camel.TypeConversionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.stereotype.Component;

@Component
public class XlateRoute extends RouteBuilder {

  @Override
  public void configure() {
    JaxbDataFormat personDataFormat = new JaxbDataFormat();
    personDataFormat.setContextPath(Person.class.getPackage().getName());
    personDataFormat.setPrettyPrint(true);

    JaxbDataFormat nextDataFormat = new JaxbDataFormat();
    nextDataFormat.setContextPath(ExecuteMatchUpdate.class.getPackage().getName());
    nextDataFormat.setPrettyPrint(true);

    this.getContext().getTypeConverterRegistry()
        .addTypeConverter(ExecuteMatchUpdate.class, Person.class, new TransformToExecuteMatch());

    onException(TypeConversionException.class, UnmarshalException.class)
        .maximumRedeliveries(3).handled(true).to("mq:q.empi.transform.dlq");

    from("mq:q.empi.deim.in").routeId("XlateRoute")
        .log("xlate")
        .unmarshal(personDataFormat)
        .to("log:com.company.app?showAll=true&multiline=true")
        .convertBodyTo(ExecuteMatchUpdate.class)
        .marshal(nextDataFormat)
        .inOnly("mq:q.empi.deim.out")
        .transform(constant("DONE"));
  }
}
