package com.mkyong.helloworld.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mkyong.helloworld.service.HelloWorldService;

@Controller
public class SnookerController {
	
	private final Logger logger = LoggerFactory.getLogger(SnookerController.class);
	private final HelloWorldService helloWorldService;
	
	@Autowired
	public SnookerController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

}
