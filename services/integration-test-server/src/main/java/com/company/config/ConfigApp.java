package com.company.config;

import com.company.app.test.PersonEJBImpl;
import com.sun.mdm.index.webservice.PersonEJB;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

  @Autowired
  private Bus bus;

  /**
   * Service implementation.
   */
  public PersonEJB personEjbImpl() {
    return new PersonEJBImpl();
  }

  /**
   * Endpoint configuration.
   */
  @Bean
  public Endpoint endpoint() {
    EndpointImpl endpoint = new EndpointImpl(bus, personEjbImpl());
    String endpointUrl = "/PersonEJBService/PersonEJB";
    String wsdlLocation = "wsdl/EMPI_18080_2.wsdl";
    endpoint.publish(endpointUrl);
    endpoint.setWsdlLocation(wsdlLocation);
    endpoint.getInInterceptors().add(new LoggingInInterceptor());
    endpoint.getOutInterceptors().add(new LoggingOutInterceptor());
    return endpoint;
  }

}
