package com.redhat.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

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

}
