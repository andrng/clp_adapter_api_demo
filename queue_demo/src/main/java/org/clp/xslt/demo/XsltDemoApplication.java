package org.clp.xslt.demo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.clp.xslt.demo.model.Constant;
import org.greeneyed.summer.config.enablers.EnableSummer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableSummer(xslt_view = true, xml_view_pooling = true, log4j = false)
@EnableJms
public class XsltDemoApplication {

    @Value("${jms.broker-url}")
    private String jmsBrokerUrl;

    @Value("${jms.user}")
    private String jmsUser;

    @Value("${jms.password}")
    private String jmsPassword;

    // for producer
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(jmsBrokerUrl);
        connectionFactory.setUserName(jmsUser);
        connectionFactory.setPassword(jmsPassword);
        return connectionFactory;
    }
    
    @Bean
    public JmsTemplate jmsQueueTemplate(){
        return new JmsTemplate(connectionFactory());
    }
    
    @Bean
    public JmsTransactionManager jmsTransactionManager(){
        return new JmsTransactionManager(connectionFactory());
    }

    // for consumer
    @Bean(name = Constant.QUEUE_CONTAINER)
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        // number of concurrent connection
        factory.setConcurrency("3-10");
        // reconnection window time
        factory.setRecoveryInterval(1000L);
        return factory;
    }
    
//    @Bean(name = Constant.TOPIC_CONTAINER)
//    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory =
//                new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setConcurrency("1");
//        factory.setPubSubDomain(true);
//        return factory;
//    }
    
	public static void main(String[] args) {
		SpringApplication.run(XsltDemoApplication.class, args);
	}

}
