package com.redhat.route;

import static org.assertj.core.api.Assertions.assertThat;

import com.redhat.MainApp;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.core.MediaType;
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
@SpringBootTest(classes = MainApp.class)
@MockEndpoints("mock:a")
public class InboundRouteCamelTest {

  @EndpointInject(uri = "mock:a")
  private MockEndpoint mock;

  @Produce(uri = "direct:start")
  private ProducerTemplate template;

  @Autowired
  private CamelContext context;

  @Test
  public void whenInboundRouteIsCalled_thenSuccess() throws Exception {
    mock.expectedMinimumMessageCount(1);
    RouteDefinition route = context.getRouteDefinition("InboundRoute");
    route.adviceWith(context, new AdviceWithRouteBuilder() {
      @Override
      public void configure() {
        weaveByToUri("mq:q.empi.deim.in").replace().to("mock:a");
      }
    });
    context.start();

    String response = (String) template.requestBodyAndHeader("direct:start",
        getSampleMessage("/SimplePatient.xml"), Exchange.CONTENT_TYPE, MediaType.APPLICATION_XML);

    assertThat(response).isEqualTo("DONE");
    mock.assertIsSatisfied();
  }

  private String getSampleMessage(String filename) throws Exception {
    return IOUtils
        .toString(this.getClass().getResourceAsStream(filename), StandardCharsets.UTF_8.name());
  }
}
