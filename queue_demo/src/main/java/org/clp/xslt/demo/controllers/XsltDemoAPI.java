package org.clp.xslt.demo.controllers;

import org.clp.xslt.demo.controllers.ptp.PtpProducer;
import org.clp.xslt.demo.model.App;
import org.clp.xslt.demo.model.Constant;
import org.clp.xslt.demo.services.PojoService;
import org.greeneyed.summer.config.XsltConfiguration.XsltModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class XsltDemoAPI {

	private final PojoService pojoService;

	@Autowired
	private PtpProducer ptpProducer;
	
    @RequestMapping(value = "/send")
    public Object send(){
        ptpProducer.send();
        return Constant.RESPONSE_SUCCESS;
    }

    @RequestMapping(value = "/convertAndSend")
    public Object convertAndSend(){
        ptpProducer.convertAndSend();
        return Constant.RESPONSE_SUCCESS;
    }	
}
