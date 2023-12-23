package com.thorben.startup;

import org.springframework.stereotype.Component;

import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkesLogger;

import jakarta.annotation.PostConstruct;

@Component
public class Startup {
	
	private static ThorbenDierkesLogger logger = new ThorbenDierkesLogger();

	@PostConstruct
    public void init(){
		long now = System.currentTimeMillis();
		logger.infoLog("Beginn Startup");
		MySql.getInstance().getOb3Updates().writeOb3Title();
		MySql.getInstance().getOb3Updates().writeOb3TitleDefinition();
		MySql.getInstance().getOb3Updates().writeOb3Filter();
		logger.infoLog("End Startup after " + (System.currentTimeMillis() - now) + "ms");
	}

}
