package org.clp.xslt.demo.controllers.ptp;

import org.clp.xslt.demo.model.Constant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PtpConsumer {

    @JmsListener(destination = Constant.QUEUE_NAME, containerFactory = Constant.QUEUE_CONTAINER)
    public void receive(String msg){
        System.out.println("point to point pattern 1: " + msg);
    }
    
}
