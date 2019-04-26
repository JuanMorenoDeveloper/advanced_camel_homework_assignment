package com.redhat.config;

import com.redhat.usecase.service.DEIMService;
import com.redhat.usecase.service.impl.DEIMServiceImpl;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.CamelContext;
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
  private CamelContext context;

  @Autowired
  private Bus bus;

  @Bean
  public Server cxfServer() {
    JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
    endpoint.setBus(bus);
    endpoint.setAddress("/");
    endpoint.setServiceBean(deimService());
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

  @Bean
  public DEIMService deimService() {
    return new DEIMServiceImpl(context);
  }
}
