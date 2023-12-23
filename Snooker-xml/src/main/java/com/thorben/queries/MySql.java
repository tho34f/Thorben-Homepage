package com.thorben.queries;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.thorben.queries.update.Ob3Updates;
import com.thorben.queries.update.UpdateDB;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MySql {
	
	private static ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
	private DataSource ds;
	private CalendarQueries calendarQueries;
	private ErrorLoggQueries errorLoggQueries;
	private NewsQueries newsQueries;
	private SnookerQueries snookerQueries;
	private UserQueries userQueries;
	private UpdateDB updateDB;
	private Ob3Updates ob3Updates;
	private OB3Queries ob3Queries;
    
    private MySql(DataSource ds) {
    	
    	this.ds = ds;
    	this.calendarQueries = new CalendarQueries(this);
    	this.errorLoggQueries = new ErrorLoggQueries(this);
    	this.newsQueries = new NewsQueries(this);
    	this.snookerQueries = new SnookerQueries(this);
    	this.userQueries = new UserQueries(this);
    	this.updateDB = new UpdateDB(this);
    	this.ob3Updates = new Ob3Updates(this);
    	this.ob3Queries = new OB3Queries(this);
    }

	public static MySql getInstance() {
    	
    	MySql sql = new MySql();
    	
        try {
        	Context ctx = new InitialContext();
        	sql = new MySql((DataSource)ctx.lookup("java:comp/env/jdbc/thorbenDB"));
        } catch (NamingException e) {
        	String erroeMessage = new StringBuilder().append(ThorbenDierkes.ERROR_DATA_SOURCE).append(e.getLocalizedMessage()).toString();
  			logger.errorLogWithTrace(ThorbenDierkes.DATA_SOURCE, erroeMessage, e);
       }
        
        logger.infoLog("Verbindung mit Datenbank hergestellt.");
        
        return sql;

    }

}
