package com.thorben.startup;

import org.springframework.stereotype.Component;

import com.thorben.service.ThorbenDierkesLogger;

import jakarta.annotation.PostConstruct;

@Component
public class Startup {
	
	private static ThorbenDierkesLogger logger = new ThorbenDierkesLogger();

	@PostConstruct
    public void init(){
		logger.infoLog("Testing Startup");
	}

}
