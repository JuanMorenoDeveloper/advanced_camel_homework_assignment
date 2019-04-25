package com.redhat.config;

import com.redhat.usecase.service.DEIMService;
import com.redhat.usecase.service.impl.DEIMServiceImpl;
import org.apache.camel.CamelContext;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

  @Autowired
  private Bus bus;
  @Autowired
  private DEIMServiceImpl service;

//  @Bean
//  public CamelContextConfiguration contextConfiguration() {
//    return new CamelContextConfiguration() {
//      @Override
//      public void beforeApplicationStart(CamelContext context) {
//        // your custom configuration goes here
////        JaxbDataFormat format = new JaxbDataFormat();
////        format.setId("personFormat");
////        format.setPartClass("com.customer.app.Person");
////        context.getDataFormats().put("personFormat", format);
//      }
//
//      @Override
//      public void afterApplicationStart(CamelContext camelContext) {
//      }
//    };
//  }

  @Bean
  public Server rsServer() {
    JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
    endpoint.setBus(bus);
    endpoint.setAddress("/");
    endpoint.setResourceClasses(DEIMServiceImpl.class);
    return endpoint.create();
  }

}
