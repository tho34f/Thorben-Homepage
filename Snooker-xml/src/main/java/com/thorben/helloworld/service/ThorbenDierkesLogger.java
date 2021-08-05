package com.thorben.helloworld.service;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorben.helloworld.queries.MySql;

public class ThorbenDierkesLogger {
	
	private static final Logger logger = LoggerFactory.getLogger(ThorbenDierkesLogger.class);
	
	public void errorLog(String title, String massage) throws SQLException, NamingException {
		MySql.getInstance().getErrorLoggQueries().newErrorLogEntry(title,massage);
		logger.info(massage);
	}
	
	public void errorLogWithTrace(String title, String massage, Exception e) throws SQLException, NamingException {
		MySql.getInstance().getErrorLoggQueries().newErrorLogEntry(title,massage);
		logger.info(massage);
		e.printStackTrace();
	}

}
