package com.thorben.startup;

import org.springframework.stereotype.Component;

import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkesLogger;

import jakarta.annotation.PostConstruct;

@Component
public class Startup {
	
	private static final ThorbenDierkesLogger LOGGER = new ThorbenDierkesLogger();

	@PostConstruct
    public void init(){
		long now = System.currentTimeMillis();
		LOGGER.infoLog("Beginn Startup");
		MySql.getInstance().getOb3Updates().writeOb3Title();
		MySql.getInstance().getOb3Updates().writeOb3TitleDefinition();
		MySql.getInstance().getOb3Updates().writeOb3Filter();
		MySql.getInstance().getOb3Updates().writeOb3FilterDefinition();
		LOGGER.infoLog("End Startup after " + (System.currentTimeMillis() - now) + "ms");
	}

}
