package com.thorben.helloworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorben.helloworld.queries.ErrorLoggQueries;

public class ThorbenDierkesLogger {
	
	private static final Logger logger = LoggerFactory.getLogger(ThorbenDierkesLogger.class);
	
	private ThorbenDierkesLogger() {
	   	
		throw new IllegalStateException("Utility Class");
	    	
    }
	
	public static void errorLog(String title, String massage) {
		ErrorLoggQueries.newErrorLogEntry(title,massage);
		logger.info(massage);
	}
	
	public static void errorLogWithTrace(String title, String massage, Exception e) {
		ErrorLoggQueries.newErrorLogEntry(title,massage);
		logger.info(massage);
		e.printStackTrace();
	}

}
