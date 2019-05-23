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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

  @Autowired
  private CamelContext context;

  @Autowired
  private Bus bus;

  @Value("${config.activemq.broker-url}")
  private String brokerUrl;

  @Value("${config.activemq.user}")
  private String user;

  @Value("${config.activemq.password}")
  private String password;

  @Value("${config.activemq.pool.max-connections}")
  private int maxConnections;

  @Value("${config.jms.max-concurrency}")
  private int maxConcurrency;

  @Value("${config.cxf.endpoint-address}")
  private String endpointAddress;

  @Bean
  public Server cxfServer() {
    JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
    endpoint.setBus(bus);
    endpoint.setAddress(endpointAddress);
    endpoint.setServiceBean(deimService());
    return endpoint.create();
  }

  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
    factory.setBrokerURL(brokerUrl);
    factory.setUserName(user);
    factory.setPassword(password);
    return factory;
  }

  public PooledConnectionFactory pooledConnectionFactory() {
    PooledConnectionFactory factory = new PooledConnectionFactory();
    factory.setMaxConnections(maxConnections);
    factory.setConnectionFactory(connectionFactory());
    return factory;
  }

  @Bean("jmsConfig")
  public JmsConfiguration jmsConfiguration() {
    JmsConfiguration config = new JmsConfiguration();
    config.setConnectionFactory(pooledConnectionFactory());
    config.setConcurrentConsumers(maxConcurrency);
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
