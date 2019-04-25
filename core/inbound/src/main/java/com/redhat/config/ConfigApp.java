package com.redhat.config;

import com.redhat.usecase.service.impl.DEIMServiceImpl;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsConfiguration;
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
  public Server cxfServer() {
    JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
    endpoint.setBus(bus);
    endpoint.setAddress("/");
    endpoint.setResourceClasses(DEIMServiceImpl.class);
    return endpoint.create();
  }

  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
    factory.setBrokerURL("failover:(tcp://127.0.0.1:61616)?maxReconnectDelay=2000");
    factory.setUserName("admin");
    factory.setPassword("password");
    return factory;
  }

  public PooledConnectionFactory pooledConnectionFactory() {
    PooledConnectionFactory factory = new PooledConnectionFactory();
    factory.setMaxConnections(1);
    factory.setConnectionFactory(connectionFactory());
    return factory;
  }

  @Bean("jmsConfig")
  public JmsConfiguration jmsConfiguration() {
    JmsConfiguration config = new JmsConfiguration();
    config.setConnectionFactory(pooledConnectionFactory());
    config.setConcurrentConsumers(1);
    return config;
  }

  @Bean("mq")
  public ActiveMQComponent activeMQComponent() {
    ActiveMQComponent component = new ActiveMQComponent();
    component.setConfiguration(jmsConfiguration());
    return component;
  }
}
