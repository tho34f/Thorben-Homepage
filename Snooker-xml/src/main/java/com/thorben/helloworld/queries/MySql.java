package com.thorben.helloworld.queries;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;


public class MySql {
	
	private static final Logger logger = LoggerFactory.getLogger(MySql.class);
	private static ThorbenDierkesLogger log = new ThorbenDierkesLogger();
	
	private Connection con;
	private DataSource ds;
	
	private CalendarQueries calendarQueries;
	private ErrorLoggQueries errorLoggQueries;
	private NewsQueries newsQueries;
	private SnookerQueries snookerQueries;
	private UserQueries userQueries;
	private UpdateDB updateDB;
    
    private MySql(DataSource ds) {
    	
    	this.ds = ds;
    	this.calendarQueries = new CalendarQueries(this, ds);
    	this.errorLoggQueries = new ErrorLoggQueries(this,ds);
    	
    }
	
    public static MySql getInstance() throws SQLException, NamingException {
    	
    	MySql sql = null;
    	
        try {
        	Context ctx = new InitialContext();
        	sql = new MySql((DataSource)ctx.lookup("java:comp/env/jdbc/thorbenDB"));
        } catch (NamingException e) {
        	String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_DATA_SOURCE).append(e.getLocalizedMessage()).toString();
  			log.errorLogWithTrace(ThorbenDierkes.DATA_SOURCE, erroeMessage, e);
       }
        
        if(sql.getDs() != null) {
        	sql.setCon(sql.getDs().getConnection());
    	}
        
        logger.info("Verbindung mit Datenbank hergestellt.");
        
        return sql;

    }

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public ErrorLoggQueries getErrorLoggQueries() {
		return errorLoggQueries;
	}

	public void setErrorLoggQueries(ErrorLoggQueries errorLoggQueries) {
		this.errorLoggQueries = errorLoggQueries;
	}

	public NewsQueries getNewsQueries() {
		return newsQueries;
	}

	public void setNewsQueries(NewsQueries newsQueries) {
		this.newsQueries = newsQueries;
	}

	public SnookerQueries getSnookerQueries() {
		return snookerQueries;
	}

	public void setSnookerQueries(SnookerQueries snookerQueries) {
		this.snookerQueries = snookerQueries;
	}

	public UserQueries getUserQueries() {
		return userQueries;
	}

	public void setUserQueries(UserQueries userQueries) {
		this.userQueries = userQueries;
	}

	public CalendarQueries getCalendarQueries() {
		return calendarQueries;
	}

	public void setCalendarQueries(CalendarQueries calendarQueries) {
		this.calendarQueries = calendarQueries;
	}

	public UpdateDB getUpdateDB() {
		return updateDB;
	}

	public void setUpdateDB(UpdateDB updateDB) {
		this.updateDB = updateDB;
	}

}
