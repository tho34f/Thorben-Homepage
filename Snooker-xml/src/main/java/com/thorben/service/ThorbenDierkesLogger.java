package com.thorben.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thorben.queries.MySql;

@Service
public class ThorbenDierkesLogger {
	
	private static final Logger LOOGER = LoggerFactory.getLogger(ThorbenDierkesLogger.class);
	
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
