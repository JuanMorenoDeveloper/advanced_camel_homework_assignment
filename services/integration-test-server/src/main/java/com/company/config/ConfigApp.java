package com.company.config;

import com.company.app.test.PersonEJBImpl;
import com.sun.mdm.index.webservice.PersonEJB;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

  @Autowired
  private Bus bus;

  /**
   * Implementación del servicio
   */
  public PersonEJB demoServiceEndpoint() {
    return new PersonEJBImpl();
  }

  /**
   * Ubicación del wsdl y el endpoint
   */
  @Bean
  public Endpoint endpoint() {
    EndpointImpl endpoint = new EndpointImpl(bus, demoServiceEndpoint());
    String endpointUrl = "/PersonEJBService/PersonEJB";
    String wsdlLocation = "wsdl/EMPI_18080_2.wsdl";
    endpoint.publish(endpointUrl);
    endpoint.setWsdlLocation(wsdlLocation);
    endpoint.getFeatures().add(new LoggingFeature());
    return endpoint;
  }

}
