package com.thorben.helloworld.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;


@Controller
public class MathController {

	private final Logger logger = LoggerFactory.getLogger(MathController.class);
	private final ThorbenDierkesService helloWorldService;
	
	Date indexDate = new Date();

	@Autowired
	public MathController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/math", method = RequestMethod.GET)
	public String start(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		return "index";
	}
	

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}


}