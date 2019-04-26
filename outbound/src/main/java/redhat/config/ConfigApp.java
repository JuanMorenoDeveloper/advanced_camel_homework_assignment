package redhat.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp {

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
