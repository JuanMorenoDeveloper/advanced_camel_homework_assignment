package com.redhat.route;

import static org.assertj.core.api.Assertions.assertThat;

import com.redhat.OutboundApp;
import java.nio.charset.StandardCharsets;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.camel.test.spring.UseAdviceWith;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@UseAdviceWith
@SpringBootTest(classes = OutboundApp.class)
@MockEndpoints
public class OutboundRouteCamelTest {

  @EndpointInject(uri = "mock:out")
  private MockEndpoint mockOut;

  @Produce(uri = "direct:start")
  private ProducerTemplate template;

  @Autowired
  private CamelContext context;

  @Test
  public void whenOutboundRouteIsCalled_thenSuccess() throws Exception {
    mockOut.expectedMinimumMessageCount(1);
    RouteDefinition route = context.getRouteDefinition("OutboundRoute");
    route.adviceWith(context, new AdviceWithRouteBuilder() {
      @Override
      public void configure() {
        replaceFromWith("direct:start");
        weaveByToUri(
            "cxf://http://localhost:8181/cxf/PersonEJBService/PersonEJB?serviceClass=com.sun.mdm.index.webservice.PersonEJB&defaultOperationName=executeMatchUpdate&dataFormat=PAYLOAD")
            .replace().to("mock:out");
      }
    });
    context.start();

    String response = (String) template.requestBodyAndHeader("direct:start",
        getSampleMessage("/executeMatchUpdateRequest.xml"), Exchange.CONTENT_TYPE,
        "application/xml");

    String body = mockOut.getExchanges().get(0).getMessage().getBody(String.class);
    assertThat(response).isEqualTo("DONE");
    assertThat(body)
        .contains("<authUser>Xlate</authUser>");
    mockOut.assertIsSatisfied();
  }

  private String getSampleMessage(String filename) throws Exception {
    return IOUtils
        .toString(this.getClass().getResourceAsStream(filename), StandardCharsets.UTF_8.name());
  }
}
