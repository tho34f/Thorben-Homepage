package com.mkyong.helloworld.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkyong.helloworld.service.DateConverter;
import com.mkyong.helloworld.service.GetHomepageData;
import com.mkyong.helloworld.service.HelloWorldService;
import com.mkyong.helloworld.service.UpdateDB;

@Controller
public class BackendController {
	
	private final Logger logger = LoggerFactory.getLogger(BackendController.class);
	private final HelloWorldService helloWorldService;
	
	@Autowired
	public BackendController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	@RequestMapping(value = "/backend/login", method = RequestMethod.GET)
	public String start(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		
		return "backend/login";
	}

}
