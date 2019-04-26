package com.redhat.route;

import com.sun.mdm.index.webservice.ExecuteMatchUpdate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.stereotype.Component;

@Component
public class OutboundRoute extends RouteBuilder {

  @Override
  public void configure() {
    JaxbDataFormat nextDataFormat = new JaxbDataFormat();
    nextDataFormat.setContextPath(ExecuteMatchUpdate.class.getPackage().getName());
    nextDataFormat.setPrettyPrint(true);

    from("mq:q.empi.deim.out").id("OutboundRoute")
        .log("outbound")
        .unmarshal(nextDataFormat)
        .to("log:com.company.app?showAll=true&multiline=true")
        .to("cxf://http://localhost:8181/cxf/PersonEJBService/PersonEJB?serviceClass=com.sun.mdm.index.webservice.PersonEJB&defaultOperationName=executeMatchUpdate&dataFormat=PAYLOAD")
        .transform(constant("DONE"));
  }
}
