package org.clp.xslt.demo.controllers.ptp;

import org.clp.xslt.demo.model.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class PtpProducer {

    @Autowired
    private JmsTemplate jmsQueueTemplate;

    public void send(){
        jmsQueueTemplate.send(Constant.QUEUE_NAME, session -> {
            return session.createTextMessage("This is the orginal message");
        });
//        throw new RuntimeException();
    }

    public void convertAndSend(){
        jmsQueueTemplate.convertAndSend(Constant.QUEUE_NAME, "This is the converted message");
    }
}
