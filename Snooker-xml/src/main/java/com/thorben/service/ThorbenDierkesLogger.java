package com.thorben.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thorben.queries.MySql;

@Service
public class ThorbenDierkesLogger {
	
	private static final Logger LOOGER = LogManager.getLogger(ThorbenDierkesLogger.class);
	
	public void errorLog(String title, String massage) {
		MySql.getInstance().getErrorLoggQueries().newErrorLogEntry(title,massage);
		LOOGER.info(massage);
	}
	
	public void errorLogWithTrace(String title, String massage, Exception e) {
		MySql.getInstance().getErrorLoggQueries().newErrorLogEntry(title,massage);
		LOOGER.info(massage);
		e.printStackTrace();
	}
	
	public void infoLog(String massage) {
		LOOGER.info(massage);
	}

}
