package com.example.RouteBuilderProject.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;

@Configuration
@Slf4j
public class ActiveMQConfig {

    @Value("${jms.host}")
    private String jmsHost;

    @Value("${jms.port}")
    private String jmsPort;

    @Value("${jms.user}")
    private String jmsUser;

    @Value("${jms.password}")
    private String jmsPassword;

    @Value("${jms.connecttimeout}")
    private String jmsConnectTimeout;

    @Value("${jms.retryattempts:6}")
    private String jmsRetryAttempts;

    @Value("${jms.prefetch:40}")
    private String prefetchCount;

    public String getConnectionURI() {
        String normalProtocol = "amqp";
        String securedProtocol = "amqps";
        String protocol = Integer.parseInt(jmsPort) == 5671 ? securedProtocol : normalProtocol;
        String connectionUrl = protocol + "://" + jmsHost + ":" + jmsPort + "/";
        log.info("Connection URL is {}", connectionUrl);
        return connectionUrl;
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        JmsConnectionFactory connFactory = new JmsConnectionFactory(jmsUser, jmsPassword, getConnectionURI());
        connFactory.setConnectTimeout(Long.parseLong(jmsConnectTimeout));
        JmsDefaultRedeliveryPolicy redeliveryPolicy = new JmsDefaultRedeliveryPolicy();
        redeliveryPolicy.setMaxRedeliveries(Integer.parseInt(jmsRetryAttempts));
        connFactory.setRedeliveryPolicy(redeliveryPolicy);
        JmsDefaultPrefetchPolicy prefetchPolicy = new JmsDefaultPrefetchPolicy();
        prefetchPolicy.setAll(Integer.parseInt(prefetchCount));
        connFactory.setPrefetchPolicy(prefetchPolicy);
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connFactory);
        cachingConnectionFactory.setCacheProducers(false);
        cachingConnectionFactory.setCacheConsumers(false);
        cachingConnectionFactory.setReconnectOnException(true);
        return cachingConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsConnectionFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}